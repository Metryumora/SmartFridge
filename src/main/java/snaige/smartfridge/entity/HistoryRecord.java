package snaige.smartfridge.entity;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Metr_yumora on 24.03.2017.
 */
@Entity
public class HistoryRecord {

    @Temporal(TemporalType.TIMESTAMP)
    Date dateTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @Column
    private String changedSetting;

    @Column
    private Double previousValue;

    @Column
    private Double newValue;

    public HistoryRecord() {
    }

    public HistoryRecord(User user, String changedSetting, Double previousValue, Double newValue) {
        this.user = user;
        dateTime = Calendar.getInstance().getTime();
        this.changedSetting = changedSetting;
        this.previousValue = previousValue;
        this.newValue = newValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getChangedSetting() {
        return changedSetting;
    }

    public void setChangedSetting(String changedSetting) {
        this.changedSetting = changedSetting;
    }

    public Double getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(Double previousValue) {
        this.previousValue = previousValue;
    }

    public Double getNewValue() {
        return newValue;
    }

    public void setNewValue(Double newValue) {
        this.newValue = newValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
