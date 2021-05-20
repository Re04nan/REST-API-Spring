package com.springrestapi.domain.exception;

public class ExceptionBusiness extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ExceptionBusiness(String mensage) {
		super(mensage);
	}
	
}
