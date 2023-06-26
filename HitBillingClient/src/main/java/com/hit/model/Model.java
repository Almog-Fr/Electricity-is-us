package com.hit.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;

public class Model implements Serializable {
    Client client = new Client();

    public Model() throws IOException {
        //client.connect();
    }

    public void connect() throws IOException {

    }

    public void disconnect(){
        client.disconnect();
    }

    public void sendRequest(String header, Map<String,String> body){
        if(!header.isEmpty() && !body.isEmpty()){
            client.sendRequest(header,body);
            client.listenForResponse();
        }

    }

    public String getResponse() throws IOException {
       return client.getLastResponse();
    }

}
