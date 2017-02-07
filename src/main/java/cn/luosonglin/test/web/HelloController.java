package cn.luosonglin.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luosonglin on 06/02/2017.
 */

@Controller
public class HelloController {

    @RequestMapping(value = "/swagger")
    public String swagger() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }
}
