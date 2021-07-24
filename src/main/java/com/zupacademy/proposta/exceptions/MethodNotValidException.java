package com.zupacademy.proposta.exceptions;

public class MethodNotValidException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MethodNotValidException(String msg){
        super(msg);
    }
}
