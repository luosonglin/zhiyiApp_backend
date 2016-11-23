package cn.luosonglin.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luosonglin on 23/11/2016.
 */

//@EnableAutoConfiguration
@RestController
public class UserController {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User view(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setName("luo");
        return user;
    }

//    public static void main(String[] args) {
//        SpringApplication.run(UserController.class);
//    }
}
