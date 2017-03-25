package snaige.smartfridge;

import java.util.Random;

/**
 * Created by Metr_yumora on 17.03.2017.
 */

public class Fridge {

    String historyFilepath = "history";

    Updater updater;

    Thread updateThread;

    private SensorSummary sensorSummary;

    private ControlPanel controlPanel;

    private History history;

    private static final int DEFAULT_UPDATE_INTERVAL = 1000;

    private long sensorUpdateInterval;

    public Fridge(String historyFilepath) {
        this.sensorSummary = new SensorSummary(
                TemperatureController.DEFAULT_UPPER_SECTION_TEMPERATURE,
                TemperatureController.DEFAULT_LOWER_SECTION_TEMPERATURE,
                TemperatureController.DEFAULT_FREEZER_SECTION_TEMPERATURE
        );
        this.controlPanel = new ControlPanel();
        sensorUpdateInterval = DEFAULT_UPDATE_INTERVAL;
        this.history = new History(historyFilepath);
        if (history.getHistoryRecords().isEmpty()) {
            this.history = new History();
        }
        updater = new Updater();
        updateThread = new Thread(updater);
        updateThread.start();

    }

    public SensorSummary getSensorSummary() {
        return sensorSummary;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public class Updater implements Runnable {
        public void run() {
            while (true) {
                updateSensors();
                try {
                    Thread.sleep(sensorUpdateInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateSensors() {
        if (sensorSummary.getUpper().getValue() == controlPanel.getUpper().getDesiredValue()) {
            sensorSummary.getUpper().setValue(sensorSummary.getUpper().getValue()
                    + Math.pow(-1, new Random().nextInt(2))
                    * sensorSummary.getUpper().getValue() * new Random().nextInt(6) / 100);
        } else {
            if (sensorSummary.getUpper().getValue() < controlPanel.getUpper().getDesiredValue()) {
                sensorSummary.getUpper().setValue(sensorSummary.getUpper().getValue() + 0.25);
            } else {
                sensorSummary.getUpper().setValue(sensorSummary.getUpper().getValue() - 0.25);
            }
        }
        if (sensorSummary.getLower().getValue() == controlPanel.getLower().getDesiredValue()) {
            sensorSummary.getLower().setValue(sensorSummary.getLower().getValue()
                    + Math.pow(-1, new Random().nextInt(2))
                    * sensorSummary.getLower().getValue() * new Random().nextInt(6) / 100);
        } else {
            if (sensorSummary.getLower().getValue() < controlPanel.getLower().getDesiredValue()) {
                sensorSummary.getLower().setValue(sensorSummary.getLower().getValue() + 0.25);
            } else {
                sensorSummary.getLower().setValue(sensorSummary.getLower().getValue() - 0.25);
            }
        }
        if (sensorSummary.getFreezer().getValue() == controlPanel.getFreezer().getDesiredValue()) {
            sensorSummary.getFreezer().setValue(sensorSummary.getFreezer().getValue()
                    + Math.pow(-1, new Random().nextInt(2))
                    * sensorSummary.getFreezer().getValue() * new Random().nextInt(6) / 100);
        } else {
            if (sensorSummary.getFreezer().getValue() < controlPanel.getFreezer().getDesiredValue()) {
                sensorSummary.getFreezer().setValue(sensorSummary.getFreezer().getValue() + 0.25);
            } else {
                sensorSummary.getFreezer().setValue(sensorSummary.getFreezer().getValue() - 0.25);
            }
        }
    }

    public void applyTempMode(double upper, double lower, double freezer) {
        if (this.controlPanel.getUpper().getDesiredValue() != upper) {
            history.getHistoryRecords().add(new HistoryRecord(
                    "User",
                    "Upper",
                    this.controlPanel.getUpper().getDesiredValue(),
                    upper
            ));
            this.controlPanel.getUpper().setDesiredValue(upper);
        }
        if (this.controlPanel.getLower().getDesiredValue() != lower) {
            history.getHistoryRecords().add(new HistoryRecord(
                    "User",
                    "Lower",
                    this.controlPanel.getLower().getDesiredValue(),
                    lower
            ));
            this.controlPanel.getLower().setDesiredValue(lower);
        }
        if (this.controlPanel.getFreezer().getDesiredValue() != freezer) {
            history.getHistoryRecords().add(new HistoryRecord(
                    "User",
                    "Freezer",
                    this.controlPanel.getFreezer().getDesiredValue(),
                    freezer
            ));
            this.controlPanel.getFreezer().setDesiredValue(freezer);
        }
        this.history.writeToFile(historyFilepath);
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public long getSensorUpdateInterval() {
        return sensorUpdateInterval;
    }

    public void setSensorUpdateInterval(long sensorUpdateInterval) {
        this.sensorUpdateInterval = sensorUpdateInterval;
    }
}
