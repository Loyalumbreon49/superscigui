module supersci.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens supersci.gui to javafx.fxml;
    exports supersci.gui;

    opens controllers to javafx.fxml;
    exports controllers;

    opens model to javafx.fxml;
    exports model;
}
