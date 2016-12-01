package cn.luosonglin.test.blog.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.LikeMapper;
import cn.luosonglin.test.blog.entity.Blog;
import cn.luosonglin.test.blog.entity.Like;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 01/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/bloglike")
public class LikeController {

    @Autowired
    private LikeMapper likeMapper;

    @ApiOperation(value = "点赞", notes = "微博点赞")
    @ApiImplicitParam(name = "like", value = "Like实体", required = true, dataType = "Like")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate likeBlog(@ModelAttribute Like like) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        Like like1 = new Like();
        like1.setUser_id(like.getUser_id());
        like1.setBlog_id(like.getBlog_id());
        like1.setCreated_at(new Date());
        likeMapper.insertByLike(like1);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }
}
