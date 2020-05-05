package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class List<Type> extends ArrayList<Type> {
    public List(){
    }



    public List(Type... arr){
        List<Type> l = new List<Type>();
        l.copyFrom(arr);
    }


    public void swap(int index1, int index2){
        move(index1, index2);
        move(index2 + 1, index1);
    }
    public void move(int initialIndex, int finalIndex){
        Type t = this.get(initialIndex);

        this.remove(initialIndex);

        this.add(finalIndex, t);

    }

    public void copyFrom(ArrayList<Type> s){
        this.clear();
        for (Type val : s) {
            this.add(val);
        }
    }

    public void copyFrom(Type[] s){
        this.clear();
        this.addAll(Arrays.asList(s));
    }

    public int length(){
        return size();
    }


    public void copyFrom(List<Type> s){
        this.clear();
        for (Type val : s) {
            this.add(val);
        }
    }

    public void add(List s) {
        this.clear();
        for (Object val : s) {
            this.add((Type) val);
        }
    }



    //String

    public void fromSentence(String s){
        this.clear();
        String[] words = s.split(" ");
        this.copyFrom((Type[]) words);
    }

    public void print(){
        System.out.println(this);
    }

    public String toString(){
        String output = "[";
        for (int i = 0; i < this.length(); i++) {
            if (i == this.length() - 1){
                output += this.get(i).toString() + "]";
            } else {
                output += this.get(i).toString() + ", ";
            }
        }
        return output;

    }

    //Integer

    public void fromInts(Integer... args){
        this.clear();
        this.copyFrom((Type[]) args);
    }

    public void limitValuesInclusively(int min, int max){
        for (int i = 0; i < this.size(); i++) {
            if ((Integer)this.get(i) >= min && (Integer)this.get(i)<= max){
                this.remove(i);
                i--;
            }
        }
    }


    public void interweave(ArrayList<Integer> s1, ArrayList<Integer> s2){
        ArrayList<Integer> output = new ArrayList<>();
        for (int i = 0; i < s1.size() + s2.size(); i++) {

            if (s2.size() < s1.size()) {
                if (isOdd(i) && i < s2.size() * 2) {
                    output.add(s2.get(i / 2));
                } else {
                    if (i < s1.size()){
                        output.add(s1.get(i / 2));
                    } else {
                        output.add(s1.get(i - s2.size()));
                    }
                }
            } else {
                if (!isOdd(i) && i < s1.size() * 2) {
                    output.add(s1.get(i / 2));
                } else {
                    if (i < s2.size()){
                        output.add(s2.get(i / 2));
                    } else {
                        output.add(s2.get(i - s1.size()));
                    }
                }
            }
        }
        copyFrom(output, (List<Integer>) this);
    }

    public void add(Type... vals){
        this.addAll(Arrays.asList(vals));
    }


    private static void copyFrom(ArrayList<Integer> toCopy, List<Integer> original){
        original.clear();
        for (Integer val : toCopy) {
            original.add(val);
        }
    }

    private static boolean isOdd(int i){
        return (i%2 == 1);
    }






}
