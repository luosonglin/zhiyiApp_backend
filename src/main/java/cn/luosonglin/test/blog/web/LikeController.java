package cn.luosonglin.test.blog.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.LikeMapper;
import cn.luosonglin.test.blog.entity.Blog;
import cn.luosonglin.test.blog.entity.Like;
import cn.luosonglin.test.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//        like1.setUser_id(like.getUser_id());
//        like1.setBlog_id(like.getBlog_id());
//        like1.setCreated_at(new Date());
        like1.setUserId(like.getUserId());
        like1.setBlogId(like.getBlogId());
        like1.setCreatedAt(new Date());
        likeMapper.insertByLike(like1);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value="取消点赞", notes="根据blog_like的id来指定更新点赞记录")
    @ApiImplicitParam(name = "like", value = "Like实体", required = true, dataType = "Like")
    @RequestMapping(value="/", method=RequestMethod.PUT)
    public ResultDate putUser(@ModelAttribute Like like) {

//        Like l = likeMapper.findById(like.getUser_id(), like.getBlog_id());
//        l.setIs_display(1);

//        Like l = likeMapper.findById(like.getUserId(), like.getBlogId());
        Like l = likeMapper.getLikeById(like.getUserId());
        l.setIsDisplay(1);
        likeMapper.cancelLike(l);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        responseMap.put("haha", l);
        responseMap.put("test", likeMapper.getLikeById(like.getUserId()));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="获取某篇微博的所有点赞的用户列表", notes="")
    @ApiImplicitParam(name = "blogId", value = "微博ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{blogId}", method=RequestMethod.GET)
    public ResultDate getUserList(@PathVariable Integer blogId) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("users", likeMapper.findAllUser(blogId));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="获取某篇微博的点赞数量", notes="")
    @ApiImplicitParam(name = "blogId", value = "微博ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{blogId}/count", method=RequestMethod.GET)
    public ResultDate getUserCount(@PathVariable Integer blogId) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("likeCount", likeMapper.getLikeCount(blogId));
        resultDate.setData(responseMap);

        return resultDate;
    }

}
