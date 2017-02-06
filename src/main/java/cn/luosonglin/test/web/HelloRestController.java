package cn.luosonglin.test.web;

import cn.luosonglin.test.service.TestProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 */
@RestController
public class HelloRestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
//        TestProperties testProperties = new TestProperties();
//        testProperties.setName("luosonglin");
//        return "Hello World "+testProperties.getName();

        return "Hello World";
    }

}