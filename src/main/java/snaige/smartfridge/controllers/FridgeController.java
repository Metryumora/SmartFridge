package snaige.smartfridge.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import snaige.smartfridge.entity.ControlPanel;
import snaige.smartfridge.entity.Fridge;
import snaige.smartfridge.entity.HistoryRecord;
import snaige.smartfridge.entity.SensorSummary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FridgeController {


    @FXML
    private Label upperLabel;

    @FXML
    private Label lowerLabel;

    @FXML
    private Label freezerLabel;

    @FXML
    private Slider cpUpperInput;

    @FXML
    private Slider cpLowerInput;

    @FXML
    private Slider cpFreezerInput;

    @FXML
    private Button btnSave;

    @FXML
    private TableView historyTable;

    @FXML
    private TableColumn<HistoryRecord, String> historyColUser;
    @FXML
    private TableColumn<HistoryRecord, String> historyColDate;
    @FXML
    private TableColumn<HistoryRecord, String> historyColTime;
    @FXML
    private TableColumn<HistoryRecord, String> historyColSection;
    @FXML
    private TableColumn<HistoryRecord, String> historyColPrevValue;
    @FXML
    private TableColumn<HistoryRecord, String> historyColNewValue;

    @FXML
    private ObservableList<HistoryRecord> observableHistory;

    private Fridge fridge = new Fridge();
    private SensorSummary summary = fridge.getSensorSummary();
    private ControlPanel panel = fridge.getControlPanel();

    @FXML
    private void applySettings() {
        fridge.applyTempMode(
                cpUpperInput.getValue(),
                cpLowerInput.getValue(),
                cpFreezerInput.getValue()
        );
    }

    @FXML
    private void initialize() {
        initTableView();
        cpUpperInput.setValue(panel.getUpper().getDesiredValue());
        cpLowerInput.setValue(panel.getLower().getDesiredValue());
        cpFreezerInput.setValue(panel.getFreezer().getDesiredValue());
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ses.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                Platform.runLater(() -> update());
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    @FXML
    private void update() {
        upperLabel.setText(summary.getUpper().toString());
        lowerLabel.setText(summary.getLower().toString());
        freezerLabel.setText(summary.getFreezer().toString());
        observableHistory = FXCollections.observableArrayList(fridge.getHistory().getHistoryRecords());
        historyTable.setItems(observableHistory);
    }

    private void initTableView() {
        observableHistory = FXCollections.observableArrayList(fridge.getHistory().getHistoryRecords());
        historyColUser = new TableColumn<HistoryRecord, String>("User");
        historyColUser.setCellValueFactory(
                new PropertyValueFactory<HistoryRecord, String>("user")
        );
        historyColDate = new TableColumn<HistoryRecord, String>("Date");
        historyColDate.setCellValueFactory(
                HistoryRecord -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
                    property.setValue(dateFormat.format(HistoryRecord.getValue().getDateTime()));
                    return property;
                }
        );
        historyColTime = new TableColumn<HistoryRecord, String>("Time");
        historyColTime.setCellValueFactory(
                HistoryRecord -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
                    property.setValue(timeFormat.format(HistoryRecord.getValue().getDateTime()));
                    return property;
                }
        );
        historyColSection = new TableColumn<HistoryRecord, String>("Section");
        historyColSection.setCellValueFactory(
                new PropertyValueFactory<HistoryRecord, String>("changedSetting")
        );
        historyColPrevValue = new TableColumn<HistoryRecord, String>("Prev. Value");
        historyColPrevValue.setCellValueFactory(
                HistoryRecord -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    property.setValue(String.format("%6.2f", HistoryRecord.getValue().getPreviousValue()));
                    return property;
                }
        );
        historyColNewValue = new TableColumn<HistoryRecord, String>("New Value");
        historyColNewValue.setCellValueFactory(
                HistoryRecord -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    property.setValue(String.format("%6.2f", HistoryRecord.getValue().getNewValue()));
                    return property;
                }
        );
        historyTable.getColumns().clear();
        historyTable.getColumns().addAll(
                historyColUser,
                historyColDate,
                historyColTime,
                historyColSection,
                historyColPrevValue,
                historyColNewValue
        );
        historyTable.setItems(observableHistory);
    }
}
