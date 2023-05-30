package com.hit.controller;

import com.hit.dao.BillDaoImpl;
import com.hit.dm.Bill;
import com.hit.service.BillService;

public class BillController {
    BillService billService = new BillService(new BillDaoImpl());

    public BillController(){

    }

    public boolean addBill(long id, String date, double sum, String ownerName){
        return billService.addNewBill(new Bill(id,date,sum,ownerName));
    }

    public boolean payBill(long billId){
        return billService.payBill(billId);
    }

    public Bill getBill(long billId){
        return billService.getBill(billId);
    }
}
