function alphFunction(currentScript, retainSession) {
  setTimeout(function() {
    function parseQueryData(urlString) {
      var queryArray = [];
      if (urlString) {
        var queryString = urlString.split('?')[1];
        if (queryString) {
          var params = queryString.split('&');
          for(var i = 0; i < params.length; i++) {
            param = params[i].split('=');
            var name = param[0];
            var value = param[1];
            queryArray.push({ name, value });
          };
        }
      };
      return queryArray;
    }

    var alphURL = currentScript.src;
    var alphQueryData = parseQueryData(alphURL);
    var alphAttributes = currentScript.attributes;
    var alphDataset = currentScript.dataset;
    if (alphQueryData.length > 0 || alphAttributes.length > 0) {
      var paramString = "?prd=web";
      var event_type = false;

      for (var i = 0; i < alphQueryData.length; i++) {
        paramString += "&"+alphQueryData[i].name+"="+alphQueryData[i].value;
        if (alphQueryData[i].name === "event_type" && alphQueryData[i].value) {
          event_type = true;
        }
      }

      for (var i = 0; i < alphAttributes.length; i++) {
        if (alphAttributes[i].name !== "async" && alphAttributes[i].name !== "type" && alphAttributes[i].name !== "src" && alphAttributes[i].name !== "id" && alphAttributes[i].name.search(/^data-/) === -1) {
          paramString += "&"+alphAttributes[i].name+"="+alphAttributes[i].value;
        }
        if (alphAttributes[i].name === "event_type" && alphAttributes[i].value) {
          event_type = true;
        }
      }

      for (const param in alphDataset) {
        paramString += "&"+param+"="+alphDataset[param];
        if (param === "event_type" && alphDataset[param]) {
          event_type = true;
        }
      }

      if (event_type === false) {
        paramString += "&event_type=visit";
      }

      var session_id;
      paramString += "&version=0.2.0";
      var timeoutValue = 18e5;
      localStorage.getItem("session_id") && ((window.document.referrer && window.document.referrer.split("/")[2] === location.hostname) || (window.performance.navigation.type === 1 || window.performance.navigation.type === 2 || retainSession === true))
                                                                        ? session_id = localStorage.getItem("session_id")
                                                                          : (session_id = Math.floor(1e6 * Math.random() + 1) + Math.round((new Date).getTime()), localStorage.setItem("session_id", session_id));
      function eventListener(e, t) {
        document.addEventListener(e, function() {
          window.clearTimeout(t), t = setTimeout(function() {}, timeoutValue)
        })
      }

      var timer = setTimeout(function() {
        sessionEnd()
        localStorage.removeItem("session_id")
      }, timeoutValue);
      eventListener("mousedown", timer), eventListener("scroll", timer);

      function decode(e) {
        return -1 != e.indexOf("%3D") ? decode(decodeURIComponent(e)) : e
      }

      function sessionEnd() {
        acb = Math.round((new Date).getTime()), (new Image).src = "https://conv-pix.adstk.io/ad/ord=" + acb + paramString + "&sess_status=en&sess=" + session_id + "&ref=" + encodeURIComponent(ref);
      }

      function sessionStart() {
        var pixel_src = "https://conv-pix.adstk.io/ad/ord=" + acb + paramString + "&utm_source=" + utm_src + "&utm_mdm=" + utm_mdm + "&url=" + encodeURIComponent(url) + "&title=" + encodeURIComponent(title) + "&sess_status=st&sess=" + session_id + "&ref=" + encodeURIComponent(ref);
        (new Image).src = pixel_src
      }

      var utm_src = "unk",
        utm_mdm = "unk",
        acb = Math.round((new Date).getTime()),
        title = window.document.title,
        url = window.location.href,
        ref = window.document.referrer;

      (url = decode(url)).indexOf("utm_source=") > -1 && (utm_src = url.substr(url.indexOf("utm_source=") + "utm_source".length + 1).split("&")[0]), 
      url.indexOf("utm_medium=") > -1 && (utm_mdm = url.substr(url.indexOf("utm_medium=") + "utm_medium".length + 1).split("&")[0]);

      sessionStart();

      window.onbeforeunload = function() {
        sessionEnd();
      }
    }
  }, 100)
};

(function(currentScript) {
  alphFunction(currentScript);

  (function(history, script){
    var pushState = history.pushState;
    history.pushState = function(state) {
      if (typeof history.onpushstate == "function") {
        history.onpushstate({state: state}, script);
      }
      return pushState.apply(history, arguments);
    }
  })(window.history, currentScript);

  (function(history, script){
    var replaceState = history.replaceState;
    history.replaceState = function(state) {
      if (typeof history.onreplacestate == "function") {
        history.onreplacestate({state: state}, script);
      }
      return replaceState.apply(history, arguments);
    }
  })(window.history, currentScript);

  window.addEventListener('popstate', (event) => {
    alphFunction(currentScript, true);
  });

  history.onpushstate = history.onreplacestate = function (event, script) {
    alphFunction(script, true);
  }

})(document.currentScript);

