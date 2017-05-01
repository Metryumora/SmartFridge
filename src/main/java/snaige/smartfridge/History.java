package snaige.smartfridge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Metr_yumora on 24.03.2017.
 */
public class History {

    private List<HistoryRecord> historyRecords;

    public void readFromDB() {
        historyRecords = HibernateUtil.getSession().createQuery("FROM HistoryRecord", HistoryRecord.class).list();
    }

    public void writeToDB() {
        for (HistoryRecord historyRecord :
                historyRecords) {
            HibernateUtil.getSession().save(historyRecord);
        }
    }

    public History() {
        this.historyRecords = new ArrayList<HistoryRecord>();
        readFromDB();
    }

    public List<HistoryRecord> getHistoryRecords() {
        return historyRecords;
    }

    public void setHistoryRecords(List<HistoryRecord> historyRecords) {
        this.historyRecords = historyRecords;
    }
}
