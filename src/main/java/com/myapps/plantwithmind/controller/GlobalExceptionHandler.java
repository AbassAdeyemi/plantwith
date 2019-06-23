package com.myapps.plantwithmind.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Value("${message.error.exception}")
	private String exceptionMessage;
	
	@Value("${message.error.duplicateUser}")
	private String duplicateUserMessage;
	
	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	String fileUploadHandler(Exception e){
		e.printStackTrace();
		
		return "Error occured uploading file";
	}
	
	
	@ExceptionHandler(value=Exception.class)
	public String defaultErrorHandler(HttpServletRequest request,Exception e,Model model){
		model.addAttribute("message",exceptionMessage);
		model.addAttribute("url",request.getRequestURL());
		model.addAttribute("exception",e);
		
		return "exception";
	}
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public String duplicateUserHandler(HttpServletRequest request,Exception e,Model model){
		
		model.addAttribute("message",duplicateUserMessage);
		model.addAttribute("url",request.getRequestURL());
		model.addAttribute("exception",e);
		System.out.println(duplicateUserMessage);
		
		
		return "exception";
	}

}
