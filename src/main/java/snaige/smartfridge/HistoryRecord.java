package snaige.smartfridge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Metr_yumora on 24.03.2017.
 */
public class HistoryRecord {

    private String user;

    private String date;

    private String time;

    private String changedSetting;

    private String previousValue;

    private String newValue;

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChangedSetting() {
        return changedSetting;
    }

    public void setChangedSetting(String changedSetting) {
        this.changedSetting = changedSetting;
    }

    public String getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public HistoryRecord(String user, String changedSetting, double previousValue, double newValue) {
        this.user = user;
        Date current = Calendar.getInstance().getTime();
        this.time = timeFormat.format(current);
        this.date = dateFormat.format(current);
        this.changedSetting = changedSetting;
        this.previousValue = String.format("%2f", previousValue);
        this.newValue = String.format("%2f", newValue);
    }

    public HistoryRecord(String user, String date, String time, String changedSetting, double previousValue, double newValue) {
        this.user = user;
        this.date = date;
        this.time = time;
        this.changedSetting = changedSetting;
        this.previousValue = String.format("%2f", previousValue);
        this.newValue = String.format("%2f", newValue);
    }
}
