package cn.luosonglin.test.caseOfIllness.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.CommentMapper;
import cn.luosonglin.test.blog.entity.Comment;
import cn.luosonglin.test.caseOfIllness.dao.CaseCommentMapper;
import cn.luosonglin.test.caseOfIllness.entity.CaseComment;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 07/01/2017.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/caseOfIllness/comment")
public class CaseCommentController {

    @Autowired
    private CaseCommentMapper commentMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;


    @ApiOperation(value="某条病例的评论列表", notes="")
    @ApiImplicitParam(name = "caseId", value = "病例ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{caseId}", method= RequestMethod.GET)
    public ResultDate getMyCommentList(@PathVariable Integer caseId) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("comment", commentMapper.getComments(caseId));
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value = "评论该病例/回复某条评论", notes = "评论病例")
    @ApiImplicitParam(name = "caseComment", value = "CaseComment实体", required = true, dataType = "CaseComment")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate postCommentMicroBlog(@ModelAttribute CaseComment caseComment) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        Map<String, Object> commentMap = new HashMap<>();
        commentMap.put("user_id", caseComment.getUserId());
        commentMap.put("case_id", caseComment.getCaseId());
        commentMap.put("comment_id", caseComment.getCommentId());
        commentMap.put("content", caseComment.getCommentId() == null ?
                caseComment.getContent():
                "回复" + userInfoMapper.getUserInfoName(commentMapper.getUserId(caseComment.getCommentId())) + "："+caseComment.getContent());
        commentMap.put("created_at", new Date());
        commentMapper.insertComment(commentMap);

        commentMapper.updateCaseCommentCount(caseComment.getCaseId());

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("comment", commentMapper.getComments(caseComment.getCaseId()));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="删除某条评论", notes="根据id来指定更新收藏记录")
    @ApiImplicitParam(name = "caseComment", value = "CaseComment实体", required = true, dataType = "CaseComment")
    @RequestMapping(value="/", method=RequestMethod.PUT)
    public ResultDate putCollection(@ModelAttribute CaseComment caseComment) {
        Map<String, Object> commentMap = new HashMap<>();
        commentMap.put("user_id", caseComment.getUserId());
        commentMap.put("case_id", caseComment.getCaseId());
        commentMap.put("comment_id", caseComment.getCommentId());
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
