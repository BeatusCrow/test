module test {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires com.google.gson;

    opens sample;
    opens questions;
}