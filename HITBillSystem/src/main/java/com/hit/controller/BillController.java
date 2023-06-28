package com.hit.controller;

import com.hit.dao.BillDaoImpl;
import com.hit.dm.Bill;
import com.hit.service.BillService;

public class BillController {
    BillService billService = new BillService(new BillDaoImpl());

    public BillController(){

    }

    public boolean addBill(long id, String date, double sum, String ownerId){
        return billService.addNewBill(new Bill(id,date,sum,ownerId));
    }

    public boolean payBill(long billId){
        return billService.payBill(billId);
    }

    public Bill getBill(long billId){
        return billService.getBill(billId);
    }

    public boolean removeBill(Bill bill){
        return billService.removeBill(bill);
    }
}
