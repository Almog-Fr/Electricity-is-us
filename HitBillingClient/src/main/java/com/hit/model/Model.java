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
        client.connect();
    }

    public void connect() throws IOException {

    }

    public void disconnect(){
        client.disconnect();
    }

    public void sendRequest(String header, Map<String,String> body){
        if(!header.isEmpty() && !body.isEmpty()){
            client.sendRequest(header,body);
        }
    }

    public Request getResponse() throws IOException {
        Request request = new Request();
       try {
           String json = client.readResponse();
           Gson gson = (new GsonBuilder()).create();
           Type type = new TypeToken<Request>(){}.getType();
           request = gson.fromJson(json,type);
       } catch (Exception e){
           e.printStackTrace();
       }
       return request;
    }

}
