package snaige.smartfridge.entity;

/**
 * Created by Metr_yumora on 17.03.2017.
 */
public class ControlPanel {

    private TemperatureController upper;

    private TemperatureController lower;

    private TemperatureController freezer;

    public ControlPanel() {
        this.upper = new TemperatureController(TemperatureController.DEFAULT_UPPER_SECTION_TEMPERATURE);
        this.lower = new TemperatureController(TemperatureController.DEFAULT_LOWER_SECTION_TEMPERATURE);
        this.freezer = new TemperatureController(TemperatureController.DEFAULT_FREEZER_SECTION_TEMPERATURE);
    }

    public TemperatureController getUpper() {
        return upper;
    }

    public void setUpper(TemperatureController upper) {
        this.upper = upper;
    }

    public TemperatureController getLower() {
        return lower;
    }

    public void setLower(TemperatureController lower) {
        this.lower = lower;
    }

    public TemperatureController getFreezer() {
        return freezer;
    }

    public void setFreezer(TemperatureController freezer) {
        this.freezer = freezer;
    }
}
