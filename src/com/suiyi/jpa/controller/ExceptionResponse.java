package com.suiyi.jpa.controller;

public class ExceptionResponse {
	private String message;
	private Integer code;

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

	public ExceptionResponse(Integer code, String message) {
		super();
		this.message = message;
		this.code = code;
	}

	public static ExceptionResponse create(Integer code, String message) {
		return new ExceptionResponse(code, message);
	}

}
