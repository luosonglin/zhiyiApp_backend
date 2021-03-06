package cn.luosonglin.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by luosonglin on 23/11/2016.
 */
@Configuration
@ComponentScan
//@ComponentScan("cn.luosonglin.test.domain") //缺少的话，部署到服务器后会找不到mapper，无法自动实例化mapper接口，@Autowired无法注入，http://stackoverflow.com/questions/36470655/spring-boot-can-not-autowired-dao
//@ComponentScan("cn.luosonglin.test.blog.entity")
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

    //限制上传的文件大小，新浪微博图片是限制5MB
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("1024KB");
        factory.setMaxRequestSize("5120KB");

        return factory.createMultipartConfig();
    }

}

