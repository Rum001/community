package com.itrum.community.community.advice;


import com.alibaba.fastjson.JSON;
import com.itrum.community.community.exception.CommunityException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(CommunityException ce, HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){
            try {
                response.setContentType("/application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                Map<String,String> map=new HashMap<>();
                map.put("message",ce.getExceptionEnum().getMsg());
                map.put("code",String.valueOf(ce.getExceptionEnum().getCode()));
                writer.write(JSON.toJSONString(map));
                writer.close();
            } catch (IOException e) {
            }
            return null;
        }else {
            modelAndView.addObject("message",ce.getExceptionEnum().getMsg());
            modelAndView.addObject("code",ce.getExceptionEnum().getCode());
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

}
