package cn.luosonglin.test.blog.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.BlogMapper;
import cn.luosonglin.test.blog.entity.Blog;
import cn.luosonglin.test.domain.UserMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 28/11/2016.
 */

@RestController
@RequestMapping(value="/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/blogs")     // 通过这里配置使下面的映射都在/blogs，可去除
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;

    @ApiOperation(value="获取博客列表", notes="")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResultDate getBlogList() {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("blogs", blogMapper.findAllBlog());
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value="发微博", notes="创建微博")
    @ApiImplicitParam(name = "blog", value = "Blog实体", required = true, dataType = "Blog")
    @RequestMapping(value="/", method=RequestMethod.POST)
    private ResultDate postMicroBlog(@ModelAttribute Blog blog) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();


        blogMapper.insertByBlog(blog);
        resultDate.setCode(200);
        responseMap.put("msg","success");
        responseMap.put("blog", blog);
        resultDate.setData(responseMap);

        System.out.print(blog.getContent());
        return resultDate;
    }

    /*
    测试，向兵帮忙解决中文乱码问题
    springboot默认就是utf-8，而且自带编码过滤器
    intellij默认也是utf-8,
    启动方式是war，那就往tomcat里面找问题，然后问题定位在server.xml

    <!-- A "Connector" represents an endpoint by which requests are received
    and responses are returned. Documentation at :
    Java HTTP Connector: /docs/config/http.html (blocking & non-blocking)
    Java AJP  Connector: /docs/config/ajp.html
    APR (HTTP/AJP) Connector: /docs/apr.html
    Define a non-SSL HTTP/1.1 Connector on port 8080
            -->
    <Connector port="8080" protocol="HTTP/1.1"
    connectionTimeout="20000"
    redirectPort="8443"
    URIEncoding="UTF-8"/>
    */
//    @ApiOperation(value="发微博test", notes="创建微博")
//    @ApiImplicitParam(name = "blog", value = "Blog实体", required = true, dataType = "Blog")
//    @RequestMapping(value="/test", method=RequestMethod.POST)
//    private ResultDate postMicroBlogByMap(@ModelAttribute Blog blog) {
//
//        ResultDate resultDate = new ResultDate();
//        Map<String, Object> responseMap = new HashMap<>();
//
//        resultDate.setCode(200);
//        responseMap.put("msg","success");
//        responseMap.put("blog", blog);
//        resultDate.setData(responseMap);
//
//        System.out.print("TAG "+blog.getContent());
//
//        return resultDate;
//    }

}
