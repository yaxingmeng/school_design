package com.suiyi.jpa.Utils;

public class ExceptionMessage extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	

    public ExceptionMessage() {
    }

    public ExceptionMessage(String message) {
        super(message);
    }

    public ExceptionMessage(String message , String code) {
        super(message);
        this.code = code;
    }

    public ExceptionMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionMessage(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

	

}
