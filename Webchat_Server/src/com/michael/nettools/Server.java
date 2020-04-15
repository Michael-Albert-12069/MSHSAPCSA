package com.michael.nettools;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    int port;
    LocalStorage messageVault;
    public Server(int port) throws IOException {
        this.port = port;
        messageVault = new LocalStorage(new File("/home/nautilus/MSHS_AP_CSA/MSHSAPCSA/Webchat_Server/webchat2.srvr"));
    }
    public void startServer() throws Exception {

        ServerSocket ss = new ServerSocket(port);
        System.out.println("--begin--");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println(socket.toString().replaceFirst("Socket", ""));

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        // read the message from the socket
        String message = dataInputStream.readUTF();
        int sPort = socket.getPort();
        String address = socket.getInetAddress().toString();
        onInput(message, sPort, address);
        System.out.println("\"" + message + "\"");


        System.out.println("---end---" + "\n" + "\n");
        ss.close();
        socket.close();
        startServer();
    }
    private void onInput(String message, int port, String address) throws Exception{

        messageVault.store(address, Integer.toString(port), message);

    }
}
