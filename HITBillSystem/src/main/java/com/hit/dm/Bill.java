package com.hit.dm;

import java.io.Serializable;

public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String date;
    private double sum;
    private String ownerId;
    private boolean isPayed = false;

    public Bill(long id, String date, double sum, String ownerName) {
        this.id = id;
        this.date = date;
        this.sum = sum;
        this.ownerId = ownerName;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }
}
