package cn.luosonglin.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by luosonglin on 24/11/2016.
 */
//@SpringBootApplication
@ComponentScan("cn.luosonglin.test.domain") //缺少的话，@autowired无法注解，http://stackoverflow.com/questions/36470655/spring-boot-can-not-autowired-dao
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
