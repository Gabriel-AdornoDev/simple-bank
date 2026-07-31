package org.gabriel.simplebank;

public class InvalidAccountHolderException extends RuntimeException {
    public InvalidAccountHolderException(String message) {
        super(message);
    }
}
