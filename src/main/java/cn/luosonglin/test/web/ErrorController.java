package cn.luosonglin.test.web;

import cn.luosonglin.test.exception.CustomizedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luosonglin on 24/11/2016.
 */
@Controller
public class ErrorController {
    @RequestMapping("/error1")
    public String error1() throws Exception {
        throw new Exception("发生错误");
    }

    //自定义错误输出，返回json格式数据
    @RequestMapping("/errorjson2")
    public String errorjson2() throws CustomizedException {
        throw new CustomizedException("发生错误2");
    }
}
