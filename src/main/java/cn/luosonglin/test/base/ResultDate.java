package cn.luosonglin.test.base;

/**
 * Created by luosonglin on 25/11/2016.
 *
 * 用于封装返回值的基类
 */
public class ResultDate <T>{
    private Integer code;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
