package cn.luosonglin.test.caseOfIllness.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.caseOfIllness.dao.CaseLikeMapper;
import cn.luosonglin.test.caseOfIllness.entity.CaseLike;
import cn.luosonglin.test.exception.CustomizedException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/caseOfIllness/like")
public class CaseLikeController {

    @Autowired
    private CaseLikeMapper caseLikeMapper;

    @ApiOperation(value = "点赞", notes = "病例点赞")
    @ApiImplicitParam(name = "caseLike", value = "CaseLike实体", required = true, dataType = "CaseLike")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate likeBlog(@ModelAttribute CaseLike caseLike) throws CustomizedException {

        if (caseLikeMapper.isLiked(caseLike.getUserId(), caseLike.getCaseId()) != null)
            throw new CustomizedException("该用户已经对该条病例点过赞");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        Map<String, Object> likeMap = new HashMap<>();
        likeMap.put("user_id", caseLike.getUserId());
        likeMap.put("case_id", caseLike.getCaseId());
        likeMap.put("created_at", new Date());
        likeMap.put("is_display", 0);

        caseLikeMapper.insertByLikeMap(likeMap);

        caseLikeMapper.updateCaseLikeCount(caseLike.getCaseId());

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value="取消点赞", notes="根据case_like的id来指定更新点赞记录")
    @ApiImplicitParam(name = "caseLike", value = "CaseLike实体", required = true, dataType = "CaseLike")
    @RequestMapping(value="/", method=RequestMethod.PUT)
    public ResultDate putUser(@ModelAttribute CaseLike caseLike) {

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        Map<String, Object> likeMap = new HashMap<>();
        likeMap.put("user_id", caseLike.getUserId());
        likeMap.put("case_id", caseLike.getCaseId());
        likeMap.put("is_display", 1);
        caseLikeMapper.cancelCaseLikeByMap(likeMap);

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="获取某篇病例的所有点赞的用户列表", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "caseId", value = "病例ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value="/{caseId}/{pageNum}/{pageSize}", method=RequestMethod.GET)
    public ResultDate getUserList(@PathVariable Integer caseId,
                                  @PathVariable Integer pageNum,
                                  @PathVariable Integer pageSize) {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        resultDate.setCode(200);
        responseMap.put("user", new PageInfo<>(caseLikeMapper.findAllUser(caseId)));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="获取某篇病例的点赞数量", notes="")
    @ApiImplicitParam(name = "caseId", value = "病例ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{caseId}/count", method=RequestMethod.GET)
    public ResultDate getUserCount(@PathVariable Integer caseId) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("likeCount", caseLikeMapper.getLikeCount(caseId));
        resultDate.setData(responseMap);

        return resultDate;
    }

}
