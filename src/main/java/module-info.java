module com.example.demo2 {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires org.kordamp.bootstrapfx.core;
  requires java.sql;
  requires freetts;

  opens GUI to javafx.fxml;
  exports GUI;
}