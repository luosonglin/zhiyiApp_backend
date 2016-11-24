package cn.luosonglin.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luosonglin on 23/11/2016.
 */
@Configuration
@ComponentScan
//@ComponentScan("cn.luosonglin.test.domain") //缺少的话，部署到服务器后会找不到mapper，无法自动实例化mapper接口，@Autowired无法注入，http://stackoverflow.com/questions/36470655/spring-boot-can-not-autowired-dao
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //spring-boot提供的解决方案：生成tomcat服务器能管理的war包，而非内嵌的tomcat直接生成的jar包
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}

