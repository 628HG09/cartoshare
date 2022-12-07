package com.example.cartoshare.exception;

public class RecordNotFoundException extends RuntimeException{
    private static final long serialVersion = 1L;

    public RecordNotFoundException(){

        super();
    }

    public RecordNotFoundException(String message) {

        super(message);

    }
}
