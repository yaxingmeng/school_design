package com.suiyi.jpa.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.suiyi.jpa.bean.ExsistsException;

@ControllerAdvice
public class ExceptionMessage {
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(Exception.class)
	public void processUnauthenticatedException(HttpServletResponse response,Exception e) throws IOException {
		e.printStackTrace();
		Writer pw=response.getWriter();
		response.setStatus(500);
		pw.write("error!!!!!");;
		pw.flush();
	}
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ExsistsException.class)
	public void exsistsException(HttpServletResponse response,ExsistsException e) throws IOException{
		e.printStackTrace();
		response.setCharacterEncoding("utf-8");
		Writer pw=response.getWriter();
		pw.write(e.getMessage());
		response.setStatus(500);
		
	}
}
