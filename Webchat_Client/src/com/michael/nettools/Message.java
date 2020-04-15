package com.michael.nettools;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Message {
    Socket socket;
    IPAddress address;
    public Message(IPAddress s, int port, String message) throws IOException {
        socket = new Socket(s.toString(), port);

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create a data output stream from the output stream so we can send data through it
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        // write the message we want to send
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush(); // send the message
        dataOutputStream.close(); // close the output stream when we're done.

        System.out.println("MESSAGE SENT!");
        socket.close();
    }
}
