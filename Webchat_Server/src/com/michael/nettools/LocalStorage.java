package com.michael.nettools;

import java.io.*;
import java.util.Scanner;

public class LocalStorage {
    File storageFile;
    private static final String FILE_PATH = "webchat";
    private static final String FILE_TYPE = ".srvr";
    public static int FILE_NUMBER = 1;

    public LocalStorage(File file) throws IOException {
        storageFile = file;
        //Create the file
        if (storageFile.createNewFile())
        {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

    }
    public LocalStorage() throws IOException {
         storageFile = new File(generateFileName());
            //Create the file
        while (!storageFile.createNewFile()) {
            FILE_NUMBER ++;
            storageFile = new File(generateFileName());
        }
        System.out.println("New File: " + storageFile.getAbsolutePath());
    }
    private static String generateFileName(){
        return FILE_PATH + FILE_NUMBER + FILE_TYPE;
    }

    public int length() throws FileNotFoundException {
        Scanner scanner = new Scanner((storageFile));
        int i = 0;
        while (scanner.hasNextLine()){
            i++;
            scanner.nextLine();
        }
        return i;
    }

    public void store(String ... datas) throws IOException {
        String line = "";
        for (int i = 0; i < datas.length - 1; i++) {
            line += datas[i] + "|";
        }
        line += datas[datas.length - 1];
        append(line + "\n");
    }




    private String getLine(int lineNumber) throws FileNotFoundException {
        Scanner scanner = new Scanner((storageFile));
        int i = 0;
        while (scanner.hasNextLine() && i < lineNumber){
            i++;
            scanner.nextLine();
        }
        return scanner.nextLine();
    }

    private void append(String s) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(storageFile.getAbsolutePath(), true));  //clears file every time
        output.append(s);
        output.close();
    }
}
