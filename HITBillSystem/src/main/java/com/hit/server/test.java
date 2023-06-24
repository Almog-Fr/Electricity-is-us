package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {
        HashMap test = new HashMap<String,String>();
        test.put("test1","test2");
        test.put("test21","test22");
        test.put("test31","test32");
        String test2 = "bill/get";
        Request request = new Request(test2,test);
        Gson gson = (new GsonBuilder()).create();
        String json = gson.toJson(request,Request.class);
        Type type = new TypeToken<Request>(){}.getType();
        Request request1 = gson.fromJson(json,type);
        System.out.println(request1.getBody().get("test1"));
    }
}
