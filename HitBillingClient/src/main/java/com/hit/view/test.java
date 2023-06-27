package com.hit.view;


import java.util.HashMap;

public class test {

    public static void main(String[] args) {
        HashMap<Integer,String> test = new HashMap<>();
        test.put(1,"hi");

        String hi = test.get(2);
        System.out.println(hi == null);

    }
}
