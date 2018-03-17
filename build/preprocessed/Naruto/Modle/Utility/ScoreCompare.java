/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Utility;

import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;


/**
 *
 * @author gia
 */
public class ScoreCompare {

    public ScoreCompare() {
    }

    public final void saveScore(int score) {
        try {
            RecordStore store = RecordStore.openRecordStore("score", true);


            String strScore = Integer.toString(score);

            RecordFilter comparator = new LowerScore(strScore);
            RecordEnumeration enumerate = store.enumerateRecords(comparator, null, true);

            if (store.getNumRecords() >= 10) {
                if (enumerate.hasNextElement()) {
                    store.deleteRecord(enumerate.nextRecordId());
                    store.addRecord(strScore.getBytes(), 0, strScore.getBytes().length);
                }
            } else {
                store.addRecord(strScore.getBytes(), 0, strScore.getBytes().length);
            }



        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }

    }

    public final String[] getHighScoreString() {
        String[] strs = new String[10];

        try {
            RecordEnumeration enumerate = RecordStore.openRecordStore("score", true).enumerateRecords(null, new CompareScore(), false);
            int i = 0;
            while (enumerate.hasNextElement()) {
                strs[i] = new String(enumerate.nextRecord());
                i++;
                //i.=i;
            }
            return strs;
        } catch (RecordStoreException ex) {
            return strs;
        }
    }
}
class CompareScore implements RecordComparator {

    public final int compare(byte[] bytes, byte[] bytes1) {
        int score1 = Integer.valueOf(new String(bytes)).intValue();
        int score2 = Integer.valueOf(new String(bytes1)).intValue();
        if (score1 == score2) {
            return RecordComparator.EQUIVALENT;
        } else if (score1 > score2) {
            return RecordComparator.PRECEDES;
        } else {
            return RecordComparator.FOLLOWS;
        }
    }
}

class LowerScore implements RecordFilter {

    private String score;

    public LowerScore(String score) {
        this.score = score;
    }

    public final boolean matches(byte[] bytes) {
        String candidate = (new String(bytes));

        if (Integer.valueOf(candidate).intValue() < Integer.valueOf(score).intValue()) {
            return true;
        }
        return false;
    }
}
