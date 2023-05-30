package com.hit.controller;

import com.hit.dao.CustomerDaoImpl;
import com.hit.dm.Customer;
import com.hit.service.CustomerService;

public class CustomerController {

    CustomerService customerService = new CustomerService(new CustomerDaoImpl());

    public CustomerController() {}

    public boolean addNewCustomer(long id,String fullName) {
        return customerService.addNewCustomer(new Customer(id,fullName));
    }

    public Customer getCustomer(long id){
        return customerService.getCustomer(id);
    }

    public boolean deleteCustomer(long id){
        return customerService.removeCustomer(id);
    }

}
