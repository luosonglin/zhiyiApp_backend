package cn.luosonglin.test.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by luosonglin on 06/02/2017.
 */
@Component
public class MyInterceptor implements HandlerInterceptor{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("***********preHandle");

        String ss[]=environment.getActiveProfiles();
        for(String a :ss){
            logger.info("environment: "+a);
        }
        String url = httpServletRequest.getRequestURL().toString();

        httpServletRequest.setAttribute("starttime", System.currentTimeMillis());

        logger.info("url: " + url);

        return true; // 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("***********postHandle");
        long starttime = (long) httpServletRequest.getAttribute("starttime");
        httpServletRequest.removeAttribute("starttime");

        long endtime = System.currentTimeMillis();
        String ip = httpServletRequest.getRemoteAddr();
        HandlerMethod handlerMethod = (HandlerMethod) o;
        // 获取用户token
        Method method = handlerMethod.getMethod();

        logger.info("request: "+ httpServletRequest.getRequestURI());
        logger.info("status: " + httpServletResponse.getStatus());
        logger.info("request time: " + (endtime - starttime) + " ms");
        logger.info("user ip: "+ip+", target: "+method.getDeclaringClass().getName() + "." + method.getName());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("***********afterCompletion");
    }
}
