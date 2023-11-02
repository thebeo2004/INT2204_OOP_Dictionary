module com.example.oop_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jlayer;
    requires freetts;

    opens com.example.oop_project to javafx.fxml;
    exports com.example.oop_project;
}