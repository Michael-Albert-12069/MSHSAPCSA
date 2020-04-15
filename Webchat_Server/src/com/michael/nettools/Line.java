package com.michael.nettools;

public class Line {
    String[] lineItems;
    public Line(String s){
        lineItems = s.split("\\|");
    }
    public IPAddress getIP() throws Exception{
        return new IPAddress(lineItems[0].substring(1));
    }
}
