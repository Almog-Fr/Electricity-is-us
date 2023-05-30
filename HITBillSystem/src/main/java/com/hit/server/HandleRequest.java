package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.controller.BillController;
import com.hit.controller.CustomerController;
import com.hit.dm.Bill;
import com.hit.dm.Customer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
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
            PrintWriter out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            Scanner scanner = new Scanner(in);
            String requestStr = scanner.nextLine();
            Request request = gson.fromJson(requestStr,Request.class);
            switch (request.getType()){
                case "bill":
                    switch (request.getAction()){
                        case "pay":
                            if(billController.payBill(Long.parseLong(request.getBody().get("id")))){
                            out.write("Bill payed successfully!");
                            out.flush();}
                            else{
                                out.write("Bill pay went wrong");
                                out.flush();
                            }
                            break;
                        case "get":
                            Bill bill = billController.getBill(Long.parseLong(request.getBody().get("id")));
                            out.write(gson.toJson(new Response("GET BILL", Map.of("Bill",bill)),Response.class));
                            out.flush();
                            break;
                        case "add":
                            billController.addBill(Long.parseLong(request.getBody().get("id")),
                                    request.getBody().get("date"),
                                    Double.parseDouble(request.getBody().get("sum")),
                                    request.getBody().get("ownerName"));
                            out.write("Bill added successfully!");
                            out.flush();
                            break;
                        default:
                            break;
                    }
                    break;
                case "customer":
                    switch (request.getAction()){
                        case "get":
                            Customer customer = customerController.getCustomer(Long.parseLong(request.getBody().get("id")));
                            out.write(gson.toJson(new Response("GET CUSTOMER", Map.of("customer",customer)),Response.class));
                            out.flush();
                            break;
                        case "add":
                            if(customerController.addNewCustomer(Long.parseLong(request.getBody().get("id")),
                                    request.getBody().get("customerName"))){
                            out.write("Customer added successfully!");
                            out.flush();}
                            else{
                                out.write("Customer addition failed");
                                out.flush();
                            }
                            break;
                        case "delete":
                            if(customerController.deleteCustomer(Long.parseLong(request.getBody().get("id")))){
                            out.write("Customer deleted successfully!");
                            out.flush();}
                            else{
                                out.write("Customer deletion failed");
                                out.flush();
                            }
                            break;
                        default:
                            break;
                    }

                    break;

                default:
                    break;
            }

            out.close();
            in.close();
            this.socket.close();
        } catch (IOException var4) {
            System.out.println("Server error");
        }

    }
}
