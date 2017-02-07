package cn.luosonglin.test;

import cn.luosonglin.test.blog.dao.BlogMapper;
import cn.luosonglin.test.blog.entity.Blog;
import cn.luosonglin.test.domain.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by luosonglin on 29/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class BlogControllerTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void testEntity() {
        Blog test = new Blog();
        test.setTitle("罗 luo");
        test.setContent("我的天空");

        System.out.println("test info:" + blogMapper.insertByBlog(test));
    }
}
