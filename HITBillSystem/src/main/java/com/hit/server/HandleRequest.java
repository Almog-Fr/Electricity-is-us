package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.controller.BillController;
import com.hit.controller.CustomerController;
import com.hit.dm.Bill;
import com.hit.dm.Customer;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HandleRequest implements Runnable {
    Gson gson = (new GsonBuilder()).create();
    Socket socket;
    BillController billController = new BillController();
    CustomerController customerController = new CustomerController();

    public HandleRequest(Socket s) {
        this.socket = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter out = new PrintWriter(new ObjectOutputStream(this.socket.getOutputStream()));
            Scanner scanner = new Scanner(in);
            String requestStr = scanner.nextLine().substring(6);
            Request request = gson.fromJson(requestStr,Request.class);
            String header = request.getHeader();

            if(header.contains("bill")){
                if(header.contains("pay")){
                    if(billController.payBill(Long.parseLong(request.getBody().get("id")))){
                        out.write("Bill payed successfully!");
                        out.flush();}
                    else{
                        out.write("Bill pay went wrong");
                        out.flush();
                    }
                }
                if(header.contains("get")){
                    Bill bill = billController.getBill(Long.parseLong(request.getBody().get("id")));
                    out.write(gson.toJson(new Response("GET BILL", Map.of("Bill",bill)),Response.class));
                    out.flush();
                }
                if(header.contains("add")){
                    billController.addBill(Long.parseLong(request.getBody().get("id")),
                            request.getBody().get("date"),
                            Double.parseDouble(request.getBody().get("sum")),
                            request.getBody().get("ownerName"));
                    out.write("Bill added successfully!");
                    out.flush();
                }
            } else if (header.contains("customer")) {
                if(header.contains("add")){
                    if(customerController.addNewCustomer(Long.parseLong(request.getBody().get("id")),
                            request.getBody().get("fullName"))){
                        HashMap<String,String> messages = new HashMap<>();
                        messages.put("message","Customer added successfully");
                        Request response = new Request("success",messages);
                        String json = gson.toJson(response,Request.class);
                        out.write(json);
                        out.flush();}
                    else{
                        HashMap<String,String> messages = new HashMap<>();
                        messages.put("message","Customer addition failed");
                        Request response = new Request("failure",messages);
                        String json = gson.toJson(response,Request.class);
                        out.write(json);
                        out.flush();
                    }
                }
                if(header.contains("get")){
                    Customer customer = customerController.getCustomer(Long.parseLong(request.getBody().get("id")));
                    out.write(gson.toJson(new Response("GET CUSTOMER", Map.of("customer",customer)),Response.class));
                    out.flush();
                }
                if(header.contains("delete")){
                    if(customerController.deleteCustomer(Long.parseLong(request.getBody().get("id")))){
                        out.write("Customer deleted successfully!");
                        out.flush();}
                    else{
                        out.write("Customer deletion failed");
                        out.flush();
                    }
                }
            }
            out.close();
            in.close();
            this.socket.close();
        } catch (IOException var4) {
            System.out.println("Server error");
        }

    }
}
