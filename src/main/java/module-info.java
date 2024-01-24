module com.example.demojeumenu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.example.demojeumenu to javafx.fxml;
    exports com.example.demojeumenu;
}