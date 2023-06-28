package com.hit.dao;

import com.hit.dm.Bill;
import com.hit.dm.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BillDaoImpl implements IDao<Long, Bill> {

    public static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\bill.txt";
    CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();

    public BillDaoImpl() {
        try {
            boolean emptyData = readData();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            if(emptyData) {
                objectOutputStream.writeObject(new HashMap<Long, Bill>());
                objectOutputStream.flush();
            }
            objectOutputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean delete(Bill bill) {
        HashMap bills;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            bills = (HashMap)objectInputStream.readObject();
            bills.remove(bill.getId());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            objectOutputStream.writeObject(bills);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Bill find(Long id) throws IllegalArgumentException {
        HashMap<Long, Bill> bills = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            bills = (HashMap<Long, Bill>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return bills.get(id);
    }

    @Override
    public boolean save(Bill bill) throws IllegalArgumentException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            Object foundObj = objectInputStream.readObject();
            HashMap<Long, Bill> bills = (HashMap<Long, Bill>) foundObj;
            bills.put(bill.getId(), bill);
            objectInputStream.close();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(FILE_PATH)));
            objectOutputStream.writeObject(bills);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
        return true;
    }

    public ArrayList<Bill> getCustomersBills(long customerId){
        ArrayList<Bill> customerBills = new ArrayList();
        HashMap<Long, Bill> allBills;
        Customer customer = customerDaoImpl.find(customerId);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            allBills = (HashMap<Long, Bill>) objectInputStream.readObject();
            for(int i = 0; i < customer.getBillIds().size();i++){
                customerBills.add(allBills.get(customer.getBillIds().get(i)));
            }
            objectInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return customerBills;
    }

    private boolean readData() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        return file.exists() || file.length() == 0;
    }
}
