package snaige.smartfridge;

import java.util.Random;

/**
 * Created by Metr_yumora on 17.03.2017.
 */

public class Fridge {

    Updater updater;

    Thread updateThread;

    private User currentUser;

    private SensorSummary sensorSummary;

    private ControlPanel controlPanel;

    private History history;

    private static final int DEFAULT_UPDATE_INTERVAL = 1000;

    private long sensorUpdateInterval;

    public Fridge() {
        currentUser = new User("User", "password");
        HibernateUtil.getSession().save(currentUser);
        this.sensorSummary = new SensorSummary(
                TemperatureController.DEFAULT_UPPER_SECTION_TEMPERATURE,
                TemperatureController.DEFAULT_LOWER_SECTION_TEMPERATURE,
                TemperatureController.DEFAULT_FREEZER_SECTION_TEMPERATURE
        );
        this.controlPanel = new ControlPanel();
        sensorUpdateInterval = DEFAULT_UPDATE_INTERVAL;
        this.history = new History();
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
        Random random = new Random();
        if (sensorSummary.getUpper().getValue() == controlPanel.getUpper().getDesiredValue()) {
            sensorSummary.getUpper().setValue(sensorSummary.getUpper().getValue()
                    + Math.pow(-1, random.nextInt(2))
                    * sensorSummary.getUpper().getValue() * (random.nextInt(6) / 100.0));
        } else {
            if (sensorSummary.getUpper().getValue() < controlPanel.getUpper().getDesiredValue()) {
                sensorSummary.getUpper().setValue(sensorSummary.getUpper().getValue() + 0.25);
            } else {
                sensorSummary.getUpper().setValue(sensorSummary.getUpper().getValue() - 0.25);
            }
        }
        if (sensorSummary.getLower().getValue() == controlPanel.getLower().getDesiredValue()) {
            sensorSummary.getLower().setValue(sensorSummary.getLower().getValue()
                    + Math.pow(-1, random.nextInt(2))
                    * sensorSummary.getLower().getValue() * (random.nextInt(6) / 100.0));
        } else {
            if (sensorSummary.getLower().getValue() < controlPanel.getLower().getDesiredValue()) {
                sensorSummary.getLower().setValue(sensorSummary.getLower().getValue() + 0.25);
            } else {
                sensorSummary.getLower().setValue(sensorSummary.getLower().getValue() - 0.25);
            }
        }
        if (sensorSummary.getFreezer().getValue() == controlPanel.getFreezer().getDesiredValue()) {
            sensorSummary.getFreezer().setValue(sensorSummary.getFreezer().getValue()
                    + Math.pow(-1, random.nextInt(2))
                    * sensorSummary.getFreezer().getValue() * (random.nextInt(6) / 100.0));
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
            HistoryRecord newRecord = new HistoryRecord(
                    currentUser,
                    "Upper",
                    this.controlPanel.getUpper().getDesiredValue(),
                    upper
            );
            history.getHistoryRecords().add(newRecord);
            HibernateUtil.getSession().save(newRecord);
            this.controlPanel.getUpper().setDesiredValue(upper);
        }
        if (this.controlPanel.getLower().getDesiredValue() != lower) {
            HistoryRecord newRecord = new HistoryRecord(
                    currentUser,
                    "Lower",
                    this.controlPanel.getLower().getDesiredValue(),
                    lower
            );
            history.getHistoryRecords().add(newRecord);
            HibernateUtil.getSession().save(newRecord);
            this.controlPanel.getLower().setDesiredValue(lower);
        }
        if (this.controlPanel.getFreezer().getDesiredValue() != freezer) {
            HistoryRecord newRecord = new HistoryRecord(
                    currentUser,
                    "Freezer",
                    this.controlPanel.getFreezer().getDesiredValue(),
                    freezer
            );
            history.getHistoryRecords().add(newRecord);
            HibernateUtil.getSession().save(newRecord);
            this.controlPanel.getFreezer().setDesiredValue(freezer);
        }
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
