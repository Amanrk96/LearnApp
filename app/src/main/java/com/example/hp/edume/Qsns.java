package com.example.hp.edume;

/**
 * Created by HP on 27-Jun-17.
 */
public class Qsns { // It Will Represent Single Data Item
    private int id;
    private String qsn, name;

    public Qsns(int id, String qsn, String name) {
        this.id = id;
        this.qsn = qsn;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQsn() {
        return qsn;
    }

    public void setQsn(String qsn) {
        this.qsn = qsn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
