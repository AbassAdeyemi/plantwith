package com.myapps.plantwithmind.controller;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletRequest;

>>>>>>> a706db3981e3b769f906d5b166b678dcb7a896dd
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
<<<<<<< HEAD
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


=======
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
>>>>>>> a706db3981e3b769f906d5b166b678dcb7a896dd

@ControllerAdvice
public class GlobalExceptionHandler {

<<<<<<< HEAD
    @Value("${message.error.duplicateUser}")
    private String duplicateUserMessage;

    @Value("${message.error.exception}")
    private String exceptionMessage;


    @ExceptionHandler(value=Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e){
        ModelAndView mv= new ModelAndView();
        mv.getModel().put("message",exceptionMessage);
        mv.getModel().put("url",request.getRequestURL());
        mv.getModel().put("exception",e);


        mv.setViewName("app.exception");
        return mv;
    }

    @ExceptionHandler(value= DataIntegrityViolationException.class)
    public String duplicateUserHandler(HttpServletRequest request, Exception e, Model model){

        model.addAttribute("message",duplicateUserMessage);
        model.addAttribute("url",request.getRequestURL());
        model.addAttribute("exception",e);

        return "exception";
    }
=======
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

>>>>>>> a706db3981e3b769f906d5b166b678dcb7a896dd
}
