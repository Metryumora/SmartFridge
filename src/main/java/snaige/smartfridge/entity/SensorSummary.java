package snaige.smartfridge.entity;

/**
 * Created by Metr_yumora on 03.03.2017.
 */
public class SensorSummary {

    private Sensor upper;

    private Sensor lower;

    private Sensor freezer;

    public SensorSummary(double upperValue, double lowerValue, double freezerValue) {
        upper = new Sensor(upperValue);
        lower = new Sensor(lowerValue);
        freezer = new Sensor(freezerValue);
    }

    public Sensor getUpper() {
        return upper;
    }

    public Sensor getLower() {
        return lower;
    }

    public Sensor getFreezer() {
        return freezer;
    }
}
