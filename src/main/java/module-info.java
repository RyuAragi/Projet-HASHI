module com.example.demojeumenu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires org.fxmisc.richtext;

    exports com.example.demojeumenu;
    opens com.example.demojeumenu to javafx.fxml;
}