package com.michael.nettools;

import com.michael.nettools.piclab.Picture;

import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket ss;
    Socket socket;
    public Framer pictureFrame;
    int port;
    public Server(int port) throws IOException {
        pictureFrame = new Framer();
        this.port = port;
    }
    public void startServer() throws Exception {

        ss = new ServerSocket(port);
        System.out.println("--begin--");
        socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println(socket.toString().replaceFirst("Socket", ""));

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        // read the message from the socket
        String message = dataInputStream.readUTF();
        int sPort = socket.getPort();
        String address = socket.getInetAddress().toString();
        new PicLoader(message, this).start();
        System.out.println("\"" + message + "\"");
        MessageDecryptor y = new MessageDecryptor(message);
        System.out.println(y.getMessage());


        System.out.println("---end---" + "\n" + "\n");

    }

}
class PicLoader extends Thread{
    String msg;
    Server s;
    public PicLoader(String message, Server server){
        super();
        msg = message;
        s = server;

    }
    @Override
    public void run() {
        super.run();
        s.pictureFrame.editorFrame.dispatchEvent(new WindowEvent( s.pictureFrame.editorFrame, WindowEvent.WINDOW_CLOSING));

        System.out.println(msg);
        Picture picture = Picture.fromText(msg);
        picture.write("G:\\Shared drives\\School_LaptopBridge\\MSHSAPCSA\\Webcam_Server\\src\\com\\michael\\nettools\\img.jpg");
        s.pictureFrame = new Framer();
        try {
            s.ss.close();
            s.socket.close();
            s.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
