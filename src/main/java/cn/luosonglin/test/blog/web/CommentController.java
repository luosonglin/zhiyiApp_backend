package cn.luosonglin.test.blog.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.CommentMapper;
import cn.luosonglin.test.blog.entity.BlogCollection;
import cn.luosonglin.test.blog.entity.Comment;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 03/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/comment")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;


    @ApiOperation(value="某条微博的评论列表", notes="")
    @ApiImplicitParam(name = "blogId", value = "微博ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{blogId}", method= RequestMethod.GET)
    public ResultDate getMyCommentList(@PathVariable Integer blogId) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("comments", commentMapper.getComments(blogId));
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value = "评论该微博/回复某条评论", notes = "评论微博")
    @ApiImplicitParam(name = "comment", value = "Comment实体", required = true, dataType = "Comment")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate postCommentMicroBlog(@ModelAttribute Comment comment) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        Map<String, Object> commentMap = new HashMap<>();
        commentMap.put("user_id", comment.getUserId());
        commentMap.put("blog_id", comment.getBlogId());
        commentMap.put("comment_id", comment.getCommentId());
        commentMap.put("content", comment.getContent());
        commentMap.put("created_at", new Date());
        commentMapper.insertComment(commentMap);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("comment", commentMapper.getComments(comment.getBlogId()));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="删除某条评论", notes="根据blog_like的id来指定更新收藏记录")
    @ApiImplicitParam(name = "comment", value = "Comment实体", required = true, dataType = "Comment")
    @RequestMapping(value="/", method=RequestMethod.PUT)
    public ResultDate putCollection(@ModelAttribute Comment comment) {
        Map<String, Object> commentMap = new HashMap<>();
        commentMap.put("user_id", comment.getUserId());
        commentMap.put("blog_id", comment.getBlogId());
        commentMap.put("comment_id", comment.getCommentId());
        commentMap.put("deleted_at", new Date());
        commentMapper.deleteComment(commentMap);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }
}
