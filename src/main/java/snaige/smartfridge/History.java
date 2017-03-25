package snaige.smartfridge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Metr_yumora on 24.03.2017.
 */
public class History {

    private List<HistoryRecord> historyRecords;


    public void writeToFile(String filename) {
        List<String> stringsHistory = new ArrayList<String>();
        for (HistoryRecord record :
                historyRecords) {
            stringsHistory.add(record.getUser() + " "
                    + record.getDate() + " "
                    + record.getTime() + " "
                    + record.getChangedSetting() + " "
                    + record.getPreviousValue() + " "
                    + record.getNewValue() + " ");
        }
        FileWorker.write(filename, stringsHistory);
    }

    public void readFromFile(String filename) {
        this.historyRecords = new ArrayList<HistoryRecord>();
        if (!new File(filename).exists()) {
            try {
                new File(filename).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        List<String> stringsHistory = FileWorker.readAllLines(filename);
        if (!stringsHistory.isEmpty()) {
            for (String record :
                    stringsHistory) {
                String[] recordValues = record.split(" ");
                recordValues[4] = recordValues[4].replace(',', '.');
                recordValues[5] = recordValues[5].replace(',', '.');
                this.historyRecords.add(new HistoryRecord(
                        recordValues[0],
                        recordValues[1],
                        recordValues[2],
                        recordValues[3],
                        Double.parseDouble(recordValues[4]),
                        Double.parseDouble(recordValues[5])
                ));
            }
        }
    }

    public History() {
        this.historyRecords = new ArrayList<HistoryRecord>();
    }

    public History(String historyFilePath) {
        readFromFile(historyFilePath);
    }

    public List<HistoryRecord> getHistoryRecords() {
        return historyRecords;
    }

    public void setHistoryRecords(List<HistoryRecord> historyRecords) {
        this.historyRecords = historyRecords;
    }
}
