module supersci.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens supersci.gui to javafx.fxml;
    exports supersci.gui;
}
