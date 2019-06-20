package com.myapps.plantwithmind.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;



@ControllerAdvice
public class GlobalExceptionHandler {

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
}
