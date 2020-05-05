package com.michael.nettools.connectivity;

import java.math.BigInteger;

public class RSA {
    public int p;
    public int q;

    public int n; // public key ( p * q)
    public int e; //exponent
    public int d; // private key
    public int privateKey;
    public int publicKey;

    private void setP(int val){
        p = val;
    }
    private void setQ(int val){
        q = val;
    }
    private void setE(int val){
        e = val;
    }

    public int getN() {
        return n;
    }

    public int getE() {
        return e;
    }
    public int getD(){
        return d;
    }

    public RSA(int p, int q, int e){
        setP(p);
        setQ(q);
        setE(e);
        n = (p*q);
        d = getPrivateKey();
        publicKey = n;

    }

    public int getPrivateKey(){
        return (2 * phi(p, q) + 1)/e;
    }

    private int phi(int p, int q){
        return ((p-1)*(q-1));
    }
    public static void primeFactorization(int n){
        // Print the number of 2s that divide n
        while (n%2==0)
        {
            System.out.print(2 + " ");
            n /= 2;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            // While i divides n, print i and divide n
            while (n%i == 0)
            {
                System.out.print(i + " ");
                n /= i;
            }
        }

        // This condition is to handle the case whien
        // n is a prime number greater than 2
        if (n > 2)
            System.out.print(n);

    }

    public int encrypt(int c){
        return (int) (Math.pow(c, e) % n);
    }

    public BigInteger decrypt(int c){
        BigInteger message = BigInteger.valueOf(c);
        return  (message.pow(d).mod(BigInteger.valueOf(n)));
    }
    static void decode(String s, int index, byte[] decode, int size) {
        if (index >= s.length())
            System.out.println(new String(decode, 0, size));
        else
            for (int i = index + 1; i <= s.length(); ++i) {
                int d = Integer.parseInt(s.substring(index, i));
                if (Character.isISOControl(d)) continue;
                if (d > 255) break;
                decode[size] = (byte)d;
                decode(s, i, decode, size + 1);
            }
    }

    static void decode(String s) {
        decode(s, 0, new byte[s.length()], 0);
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
