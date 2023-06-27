package com.hit.service;

import com.hit.dao.BillDaoImpl;
import com.hit.dm.Bill;

public class BillService {


    BillDaoImpl billDao;

    public BillService(BillDaoImpl billDao) {
        this.billDao = billDao;
    }

    public boolean payBill(Long billId){
        Bill bill = billDao.find(billId);
        if(bill == null)
            return false;
        bill.setPayed(true);
        billDao.save(bill);
        return true;
    }


    public Bill getBill(long billId) {
        Bill bill = billDao.find(billId);
        if(bill == null)
            return null;
        return bill;
    }

    public boolean addNewBill(Bill bill) {
        if(bill == null)
            return false;
        return billDao.save(bill);
    }

    public boolean removeBill(Bill bill) {
        if(bill == null)
            return false;
        billDao.delete(bill);
        return true;
    }

}
