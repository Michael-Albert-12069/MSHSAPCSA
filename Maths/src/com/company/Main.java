package com.company;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(nthPrime(i));
        }
    }

    public static int nthPrime(int n){
        n ++;
        int number = 2;
        while (n > 1){
            if (isPrime(number)){
                n--;
            }
            number++;
        }
        return number-1;
    }

    public static boolean isPrime(int i){
        for (int j = 2; j < i; j++) {
            if (i % j == 0){
                return false;
            }
        }
        return true;
    }
}
