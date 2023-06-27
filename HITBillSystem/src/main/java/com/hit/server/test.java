package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.hit.controller.CustomerController;
import com.hit.dm.Customer;

import java.lang.reflect.Type;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {

        CustomerController customerController = new CustomerController();
        Customer customer = customerController.getCustomer(1234l);
        System.out.println(customer);
    }
}
