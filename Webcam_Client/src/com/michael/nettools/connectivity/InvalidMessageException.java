package com.michael.nettools.connectivity;

public class InvalidMessageException extends Exception {
     public InvalidMessageException() {
            super("The Message contains an illegal character");
     }
}

