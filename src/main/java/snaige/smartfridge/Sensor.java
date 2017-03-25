package snaige.smartfridge;

/**
 * Created by Metr_yumora on 03.03.2017.
 */
public class Sensor {

    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Sensor(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%6.2f", getValue()) + "°C";
    }
}
