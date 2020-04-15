package com.michael.nettools;

public class InvalidMessageException extends Exception {
     public InvalidMessageException() {
            super("The Message contains an illegal character");
     }
}

