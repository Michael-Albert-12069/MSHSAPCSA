package com.michael.nettools.connectivity;

public class Client {
    IPAddress ipAddress;
    int port;
    public Client (IPAddress ipAddress, int port){
        this.ipAddress = ipAddress;
        this.port = port;
    }
    public void sendMessage(String message) throws Exception{
        if (message.contains("|")){throw new InvalidMessageException();}
        Message m = new Message(ipAddress, port, message);
    }
}
