package snaige.smartfridge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label upperLabel;

    @FXML
    private Label lowerLabel;

    @FXML
    private Label freezerLabel;

    @FXML
    private void dynamicUpdate() {
        SensorSummary summary = new SensorSummary();
        upperLabel.setText(summary.getUpper().toString());
        lowerLabel.setText(summary.getLower().toString());
        freezerLabel.setText(summary.getFreezer().toString());
    }
}
