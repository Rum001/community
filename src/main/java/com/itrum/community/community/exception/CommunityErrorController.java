package com.itrum.community.community.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class CommunityErrorController implements ErrorController {

    @RequestMapping(produces = {"text/html"})
    public ModelAndView errorHtml(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        ModelAndView modelAndView = new ModelAndView();
        if (status.is4xxClientError()) {
            modelAndView.addObject("message","您输入的地址出错啦！请重新输入吧！");
            modelAndView.addObject("code",status);
        }
        if (status.is5xxServerError()){
            modelAndView.addObject("message","服务器冒烟啦~~~ 请稍后再试试吧!");
            modelAndView.addObject("code",status);
        }
       modelAndView.setViewName("error");
        return modelAndView;
    }
    public HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }
    @Override
    public String getErrorPath() {
        return "error";
    }
}
