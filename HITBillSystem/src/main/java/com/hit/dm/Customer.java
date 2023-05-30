package com.hit.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    long id;
    String fullName;
    double balance = 0;
    List<Long> billIds = new ArrayList();

    public Customer(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Long> getBillIds() {
        return billIds;
    }

    public boolean addBill(Bill bill){
        if(bill != null){
            billIds.add(bill.getId());
            this.balance += bill.getSum();
            return true;
        }

        return false;
    }
}
