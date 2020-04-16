package com.michael.nettools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class MessageEncryptor {
    IPAddress ipAddress;
    String msg;
    public MessageEncryptor(String message) throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        ipAddress = new IPAddress(inetAddress.getHostAddress());
//        System.out.println(this.q());
//        System.out.println(this.p());
//        System.out.println(this.e());

        msg = message;
    }


    public String getMessage(){
        RSA rsa = new RSA(this.p(), this.q(), this.e());
        byte[] arr = msg.getBytes();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (byte) rsa.encrypt(arr[i]);
        }
        return new String(arr);
    }
    private int q(){
        return Math.max(RSA.nthPrime(ipAddress.getOctet(0) * ipAddress.getOctet(3)), RSA.nthPrime(ipAddress.getOctet(1) * ipAddress.getOctet(3)));
    }
    private int p(){
        return Math.min(RSA.nthPrime(ipAddress.getOctet(0) * ipAddress.getOctet(3)), RSA.nthPrime(ipAddress.getOctet(1) * ipAddress.getOctet(3)));
    }
    private int e(){
        return RSA.nthPrime((int) Math.pow(ipAddress.getOctet(3), ipAddress.getOctet(3)));
    }
}
