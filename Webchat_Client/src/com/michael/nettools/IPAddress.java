package com.michael.nettools;

public class IPAddress {
    String Addr;
    String[] octets;
    public IPAddress(String Address) throws InvalidIPAddress {
        octets = Address.split("\\.");
        if (octets.length != 4){
            throw new InvalidIPAddress();
        }else{
            Addr = Address;
        }
        System.out.println(Address);
    }

    public int getOctet(int i){
        return Integer.parseInt(octets[i]);
    }

    public String toString(){
        return Addr;
    }
}
