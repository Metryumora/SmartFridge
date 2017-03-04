package snaige.smartfridge;

/**
 * Created by Metr_yumora on 03.03.2017.
 */
public class SensorSummary {

    private Sensor upper;

    private Sensor lower;

    private Sensor freezer;

    public Sensor getUpper() {
        return upper;
    }

    public void setUpper(Sensor upper) {
        this.upper = upper;
    }

    public Sensor getLower() {
        return lower;
    }

    public void setLower(Sensor lower) {
        this.lower = lower;
    }

    public Sensor getFreezer() {
        return freezer;
    }

    public void setFreezer(Sensor freezer) {
        this.freezer = freezer;
    }

    public SensorSummary() {
        upper = new Sensor();
        lower = new Sensor();
        freezer = new Sensor();
        upper.update(0,4);
        lower.update(0,4);
        freezer.update(0,4);
    }
}
