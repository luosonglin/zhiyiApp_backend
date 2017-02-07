package cn.luosonglin.test.exception;

import cn.luosonglin.test.exception.dto.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by luosonglin on 24/11/2016.
 *
 * 全局异常处理类
 */
@ControllerAdvice       //使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)  //@ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    //只需在@ExceptionHandler之后加入@ResponseBody，就能让处理函数return的内容转换为JSON格式
    @ExceptionHandler(value = CustomizedException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, CustomizedException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
