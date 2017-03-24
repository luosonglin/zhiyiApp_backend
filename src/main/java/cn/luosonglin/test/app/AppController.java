package cn.luosonglin.test.app;

import cn.luosonglin.test.app.entity.LoginActivity;
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
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation(value = "第三方登录(判断要不要去手机页面)", notes = "")
    @ApiImplicitParam(name = "loginActivity", value = "用户platform,openId,qqOpenId", required = true, dataType = "LoginActivity")
    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    public ResultDate getNextPage(@ModelAttribute LoginActivity loginActivity) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        UserInfo userInfo = null;

        if (loginActivity.getLoginSource().equals("WeChat")) {
            userInfo = userInfoMapper.getUserInfoByOpenId(loginActivity.getOpenId());
        } else if (loginActivity.getLoginSource().equals("QQ")) {
            userInfo = userInfoMapper.getUserInfoByQQOpenId(loginActivity.getQqOpenId());
        }

        if (userInfo == null) {
            resultDate.setCode(200);
            responseMap.put("sc", "1000");//status_code
            responseMap.put("t", "tlp");//target
            responseMap.put("user", null);
        } else {
            resultDate.setCode(200);
            responseMap.put("sc", "1001");
            responseMap.put("t", "ntlp");//not to Login Page
            if (loginActivity.getLoginSource().equals("WeChat")) {
                responseMap.put("user", userInfoMapper.getUserInfoByOpenId(loginActivity.getOpenId()));
            } else if (loginActivity.getLoginSource().equals("QQ")) {
                responseMap.put("user", userInfoMapper.getUserInfoByQQOpenId(loginActivity.getQqOpenId()));
            }

        }
        responseMap.put("msg", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }


}
