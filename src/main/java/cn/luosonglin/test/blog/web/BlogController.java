package cn.luosonglin.test.blog.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.BlogMapper;
import cn.luosonglin.test.domain.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
