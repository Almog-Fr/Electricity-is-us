package com.hit.model;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Client {
    private Socket clientSocket= new Socket("localhost",12345);;
    private ObjectOutputStream objectOutputStream;
    private InputStreamReader inputSteamReader;

    public Client() throws IOException {
        inputSteamReader = new InputStreamReader(clientSocket.getInputStream());
    }

    public void connect() throws IOException {



    }

    public void sendRequest(String action,Map<String, String> body){
        try{
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            Gson gson = (new GsonBuilder()).create();
            Request request = new Request(action,body);
            String json = gson.toJson(request,Request.class);
            PrintWriter out = new PrintWriter(objectOutputStream);
            out.write(json);
            out.flush();
            out.close();

        }catch(Exception re){
            re.printStackTrace();
        }

    }

    public String readResponse() throws IOException {

        BufferedReader in = new BufferedReader(inputSteamReader);
        Scanner scanner = new Scanner(in);
        //while(!scanner.hasNextLine()){}
        Stream<String> json = in.lines();
//        String json = scanner.nextLine();
        return "json";
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
