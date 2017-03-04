package snaige.smartfridge;

import java.util.Random;

/**
 * Created by Metr_yumora on 03.03.2017.
 */
public class Sensor {

    private double value;

    private long updateInterval;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(long updateInterval) {
        this.updateInterval = updateInterval;
    }

    public void update(int lowerBound, int upperBound){

        setValue(lowerBound + new Random().nextInt(lowerBound+upperBound)+ new Random().nextInt(100)/100.0);
    }

    @Override
    public String toString() {
        return String.format("%6.2f",getValue())+"°C";
    }
}
