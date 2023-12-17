module test {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires com.google.gson;
    requires java.sql;
    requires java.sql.rowset;
    requires mysql.connector.j;

    opens sample;
    opens questions;
}