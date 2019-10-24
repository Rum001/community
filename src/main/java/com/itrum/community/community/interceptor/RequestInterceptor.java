package com.itrum.community.community.interceptor;

import com.itrum.community.community.domain.User;
import com.itrum.community.community.domain.UserExample;
import com.itrum.community.community.enums.ExceptionEnum;
import com.itrum.community.community.exception.CommunityException;
import com.itrum.community.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    UserExample example = new UserExample();
                    example.createCriteria().andTokenEqualTo(cookie.getValue());
                    List<User> users = userMapper.selectByExample(example);
                    if (users!=null){
                        if (request.getSession().getAttribute("user")==null){
                            request.getSession().setAttribute("user", users.get(0));
                        }
                        return true;
                    }
                    break;
                }else {
                    throw new CommunityException(ExceptionEnum.USER_NOT_LOGIN);
                }
            }
        }else {
            throw new CommunityException(ExceptionEnum.USER_NOT_LOGIN);
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
