package cn.luosonglin.test.exception;

/**
 * Created by luosonglin on 24/11/2016.
 *
 * 自定义异常，用来实验捕获该异常，并返回json
 */
public class CustomizedException extends Exception{

    public CustomizedException(String message) {
        super(message);
    }
}
