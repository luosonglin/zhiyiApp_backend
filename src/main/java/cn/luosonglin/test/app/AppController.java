package cn.luosonglin.test.app;

import cn.luosonglin.test.banner.dao.EventBannerMapper;
import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.BlogMapper;
import cn.luosonglin.test.blog.entity.Blog;
import cn.luosonglin.test.caseOfIllness.dao.CaseMapper;
import cn.luosonglin.test.caseOfIllness.entity.CaseOfIllness;
import cn.luosonglin.test.event.dao.EventMapper;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.member.entity.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 10/03/2017.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/app")
public class AppController {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    CaseMapper caseMapper;

    @Autowired
    EventMapper eventMapper;

    @ApiOperation(value = "test  有删库操作，切勿请求", notes = "勿动！！！")
    @ApiImplicitParam(name = "user_id", value = "用户ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public ResultDate getUserInfo(@PathVariable Integer user_id) throws CustomizedException {

        if (user_id == null)
            throw new CustomizedException("user_id不可为空");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        UserInfo userInfo = userInfoMapper.getUserInfoByUserId(user_id);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("user", userInfo);
        resultDate.setData(responseMap);
        return resultDate;
    }

    @ApiOperation(value = "首页混合推荐列表", notes = "app首页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/index/{userId}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultDate getMixRecommandList(@PathVariable Integer userId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

//        if (pageNum != null && pageSize != null) {
//            PageHelper.startPage(pageNum, pageSize);
//        }

        List<Object> list = new ArrayList<>();
        list.add(eventMapper.findAllEvent());
        list.add(blogMapper.getRecommendBlog());
        list.add(caseMapper.findAllCase());

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("list", list);

//        responseMap.put("event", eventMapper.findAllEvent());
//        responseMap.put("blog", blogMapper.getRecommendBlog());
//        responseMap.put("case", caseMapper.findAllCase());

//        responseMap.put("blog", new PageInfo<>(blogMapper.getRecommendBlog()));
//        responseMap.put("case", new PageInfo<>(caseMapper.findAllCase()));
        resultDate.setData(responseMap);
        return resultDate;
    }




}
