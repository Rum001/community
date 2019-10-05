package com.itrum.community.community.advice;


import com.itrum.community.community.exception.CommunityException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(CommunityException ce){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",ce.getExceptionEnum().getMsg());
        modelAndView.addObject("code",ce.getExceptionEnum().getCode());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
