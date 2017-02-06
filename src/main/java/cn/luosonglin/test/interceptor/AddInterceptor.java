package cn.luosonglin.test.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by luosonglin on 06/02/2017.
 */
@Configuration
public class AddInterceptor extends WebMvcConfigurerAdapter{

    @Bean
    public HandlerInterceptor getMyInterceptor() {
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 排除拦截
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**"); //   /v2/api-docs
        super.addInterceptors(registry);
    }

}
