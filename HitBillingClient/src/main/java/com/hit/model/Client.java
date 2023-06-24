package com.hit.model;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private InputStreamReader inputSteamReader;

    public Client() {
    }

    public void connect() throws IOException {
        clientSocket = new Socket("localhost",12345);
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inputSteamReader = new InputStreamReader(clientSocket.getInputStream());
    }

    public void sendRequest(String action,Map<String, String> body){
        Gson gson = (new GsonBuilder()).create();
        Request request = new Request(action,body);
        String json = gson.toJson(request,Request.class);
        PrintWriter out = new PrintWriter(objectOutputStream);
        out.write(json);
        out.flush();
        out.close();
    }

    public String readResponse() throws IOException {
        BufferedReader in = new BufferedReader(inputSteamReader);
        Scanner scanner = new Scanner(in);
        return scanner.nextLine();
    }

    public void disconnect(){
        try{
            if(clientSocket.isConnected()){
                objectOutputStream.close();
                inputSteamReader.close();
                clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
