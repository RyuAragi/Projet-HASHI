module com.example.demojeumenu {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires org.fxmisc.richtext;
    requires org.slf4j;
    requires java.logging;

    opens com.example.demojeumenu to javafx.fxml;
    exports com.example.demojeumenu;
}