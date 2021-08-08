package com.sana.blogadmin.exception;

public class PostByUserProcessException extends RuntimeException {
    public PostByUserProcessException() {
    }

    public PostByUserProcessException(String message) {
        super(message);
    }
}
