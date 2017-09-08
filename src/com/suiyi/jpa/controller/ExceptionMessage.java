package com.suiyi.jpa.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionMessage {
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(Exception.class)
	public void processUnauthenticatedException(HttpServletResponse response,Exception e) throws IOException {
		Writer pw=response.getWriter();
		response.setStatus(500);
		pw.write("error!!!!!");;
		pw.flush();
	}

}
