package com.michael.nettools;

import com.michael.nettools.connectivity.Client;
import com.michael.nettools.connectivity.IPAddress;
import com.michael.nettools.connectivity.InvalidIPAddress;

import java.util.Scanner;


public class Main {

    public static IPAddress ipAddress;
    public static int port;

    public static void main(String[] args) throws Exception {
        ipAddress = new IPAddress(getAnswer("IP Address: "));
        port = getNumber("port#: ");


    }

    public static void sendMessage() throws Exception {
        IPAddress address = new IPAddress("192.168.1.11");
        int port = 17487;
        Client connection = new Client(address, port);
        while (true){
            String message = getAnswer("Message: ");
            if (message.equals("<exit>")){System.exit(0);}

            connection.sendMessage("");
            clearScreen();
            Thread.sleep(1000);
        }
    }






    //previously the IO class

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";


    public static boolean getYN(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(RED + question + " (y/n): " + RESET);
        String ans = scanner.nextLine();
        if (ans.toLowerCase().equals("y")) {
            return true;
        } else {
            return false;
        }

    }

    public static String getAnswer(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        return scanner.nextLine();
    }

    public static int getNumber(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        return scanner.nextInt();
    }

    public static int getNumber(String question, int max) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        int ans = scanner.nextInt();
        if (ans > max) {
            System.out.println("Try again");
            return getNumber(question, max);
        } else {
            return ans;
        }
    }

    public static void print(String color, String message) {
        System.out.println(color + message + RESET);
    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }
}
