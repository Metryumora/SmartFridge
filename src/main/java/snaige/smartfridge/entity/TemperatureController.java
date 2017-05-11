package snaige.smartfridge.entity;

/**
 * Created by Metr_yumora on 17.03.2017.
 */
public class TemperatureController {

    public static final double DEFAULT_UPPER_SECTION_TEMPERATURE = 5;

    public static final double DEFAULT_LOWER_SECTION_TEMPERATURE = 2;

    public static final double DEFAULT_FREEZER_SECTION_TEMPERATURE = -3;

    private double desiredValue;

    public TemperatureController(double desiredValue) {
        this.desiredValue = desiredValue;
    }

    public double getDesiredValue() {
        return desiredValue;
    }

    public void setDesiredValue(double desiredValue) {
        this.desiredValue = desiredValue;
    }
}
