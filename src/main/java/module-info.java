module com.example.demojeumenu {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires org.fxmisc.richtext;
    requires org.slf4j;
    requires java.logging;
    requires java.desktop;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;

    opens com.example.demojeumenu to javafx.fxml;
    exports com.example.demojeumenu;
    exports com.example.demojeumenu.utils;
    opens com.example.demojeumenu.utils to javafx.fxml;
    exports com.example.demojeumenu.controler;
    opens com.example.demojeumenu.controler to javafx.fxml;
    exports com.example.demojeumenu.technique;
    opens com.example.demojeumenu.technique to javafx.fxml;
    exports com.example.demojeumenu.undoRedo;
    opens com.example.demojeumenu.undoRedo to javafx.fxml;
    exports com.example.demojeumenu.Menu;
    opens com.example.demojeumenu.Menu to javafx.fxml;
    exports com.example.demojeumenu.game;
    opens com.example.demojeumenu.game to javafx.fxml;
}