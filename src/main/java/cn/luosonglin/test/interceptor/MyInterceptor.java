package cn.luosonglin.test.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by luosonglin on 06/02/2017.
 */
@Component
public class MyInterceptor implements HandlerInterceptor{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("***********preHandle");
        httpServletRequest.setAttribute("starttime", System.currentTimeMillis());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("***********postHandle");
        long starttime = (long) httpServletRequest.getAttribute("starttime");
        httpServletRequest.removeAttribute("starttime");

        long endtime = System.currentTimeMillis();
        logger.info("***********==========请求"+ httpServletRequest.getRequestURI() + "的处理时间：" + (endtime - starttime));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("***********afterCompletion");
    }
}
