package cn.yj.notes.common.web;

import cn.yj.notes.common.annotation.Authentication;
import cn.yj.notes.common.web.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <br>
 *
 * @author 永健
 * @since 2019/5/12 19:04
 */
@Slf4j
public class Interceptor implements HandlerInterceptor
{

    private Auth auth;

    public Interceptor(Auth auth)
    {
        this.auth = auth;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, PUT, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers",
                "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Accept");

        log.warn("sessionId={}",request.getSession().getId());


        // 权限拦截
        if(handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Authentication authentication = method.getAnnotation(Authentication.class);
            if (authentication !=null){
                auth.isReqToken();
            }
        }
        return true;
    }
}
