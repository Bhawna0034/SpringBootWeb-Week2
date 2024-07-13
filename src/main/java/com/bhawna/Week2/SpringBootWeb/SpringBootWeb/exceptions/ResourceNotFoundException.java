package com.bhawna.Week2.SpringBootWeb.SpringBootWeb.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
