package com.michael.nettools;

import java.io.*;
import java.net.URL;
import java.util.Random;

public class Dictionary {
    public WordTable map;
    public static boolean contains;


    public Dictionary(){
        map = new WordTable();
    }
    //constructors
    public Dictionary(Dictionary... dictionaries){
        map = new WordTable();
        int l = 0;
        for (Dictionary dict:dictionaries) {
            for (int i = 0; i < dict.length(); i++) {
                map.put(l, dict.getWord(i));
                l ++;
            }
        }
        map.removeDuplicates();
    }
    public Dictionary(File file){
        map =  new WordTable();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                map.put(i, line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Dictionary(URL link) throws IOException {
        map =  new WordTable();


        BufferedReader in = new BufferedReader(
                new InputStreamReader(link.openStream()));

        String line;
        int i = 0;
        while ((line = in.readLine()) != null) {
            map.put(i, line);
            i++;
        }
        in.close();
    }


    public void add(String s){
        map.put(map.size(), s);
    }
    //methods

    /** @param s - is the string that you wish to verify against (IS CASE SENSITIVE)
     * @param verbose - is whether you want the verbose output as the program goes through
     * @return - words that it has in common with the dictionary (min length > 2)
     */
    public String containsDictWord(String s, boolean verbose){
        String containedWord = "";
        boolean output = false;
        for (int i = 0; i < map.size(); i++) {
            if (s.contains(map.get(i)) && map.get(i).length() > 2){
                output = true;
                containedWord += map.get(i) + ": " + i +", ";
            }
        }
        if (!verbose){
            System.out.print(containedWord);
            return String.valueOf(output);
        } else {
            return output + containedWord;
        }
    }


    /** @param s - is the string that you wish to verify against (IS CASE SENSITIVE)
     * @return - whether the word exists
     */
    public boolean containsDictWord(String s){
        String containedWord = "";
        boolean output = false;
        for (int i = 0; i < map.size(); i++) {
            if (s.contains(map.get(i)) && map.get(i).length() > 2){
                output = true;
            }
        }

        return output;
    }


    public String randomWord(){
        Random index = new Random();
        int randIndex = index.nextInt(map.size());
        return map.get(randIndex);
    }

    //accessors
    public WordTable getWordTable(){
        return map;
    }

    public String getWord(int index){
        return map.get(index);
    }

    public void toFile(File f) throws IOException {
        for (int i = 0; i < map.size(); i++) {
            appendLine(this.getWord(i), f.getAbsolutePath());
        }
    }

    private static void appendLine(String s, String filename) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(filename, true));  //clears file every time
        if (!isNull(s)){
            output.append(s + "\n");
        }
        output.close();
        System.out.println(s);
    }

    public int length(){
        return map.size();
    }

    //universal java methods
    public String toString(){
        if (map.isEmpty()) {
            return ("map is empty");
        } else {
            String out = "";
            for (int i = 0; i < map.size(); i++) {
                if (!isNull(map.get(i))){
                    out += map.get(i) + "\n";
                }

            }
            return out;
        }
    }

    public boolean contains(String s){
        map.forEach((key, value) ->{
            if (value.contains(s)){
                Dictionary.contains = true;
            }
        });
        if (Dictionary.contains){
            return true;
        } else {
            return false;
        }
    }

    private static boolean isNull(String s){
        return ((s == null || s.equals("null")) || s.equals(""));
    }

}
