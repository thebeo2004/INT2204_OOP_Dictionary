module GUI {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires org.kordamp.bootstrapfx.core;
  requires java.sql;
  requires freetts;
  requires jlayer;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens GUI to javafx.fxml;
  exports GUI;
}