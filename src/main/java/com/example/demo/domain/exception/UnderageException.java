package com.example.demo.domain.exception;

public class UnderageException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UnderageException(String message) {
        super(message);
    }

}
