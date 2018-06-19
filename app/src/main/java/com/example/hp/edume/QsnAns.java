package com.example.hp.edume;

/**
 * Created by HP on 22-Jun-17.
 */
public class QsnAns {
    private int id;
    private String qsn, ans;

    public QsnAns(String qsn, String ans, int id) {
        this.qsn = qsn;
        this.ans = ans;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQsn(String qsn) {
        this.qsn = qsn;
    }

    public String getQsn() {
        return qsn;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getAns() {
        return ans;
    }

}