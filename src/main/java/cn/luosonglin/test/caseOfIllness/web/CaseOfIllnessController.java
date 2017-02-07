package cn.luosonglin.test.caseOfIllness.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.caseOfIllness.dao.CaseCollectionMapper;
import cn.luosonglin.test.caseOfIllness.dao.CaseLikeMapper;
import cn.luosonglin.test.caseOfIllness.dao.CaseMapper;
import cn.luosonglin.test.caseOfIllness.entity.CaseOfIllness;
import cn.luosonglin.test.caseOfIllness.entity.UserAndCase;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.relationship.dao.UsersRelationshipMapper;
import cn.luosonglin.test.relationship.entity.UsersRelationship;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 07/01/2017.
 */

@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/caseOfIllness")
public class CaseOfIllnessController {

    @Autowired
    private CaseMapper caseMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UsersRelationshipMapper usersRelationshipMapper;

    @Autowired
    private CaseLikeMapper caseLikeMapper;

    @Autowired
    private CaseCollectionMapper caseCollectionMapper;



    @ApiOperation(value = "获取病例列表", notes = "")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultDate getBlogList() {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("case", caseMapper.findAllCase());
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "某一条病例的详情", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "case_id", value = "病例的ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "from_uid", value = "谁去看这篇病例", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/{case_id}/{from_uid}/detail", method = RequestMethod.GET)
    public ResultDate getBlogDetail(@PathVariable Integer case_id, @PathVariable Integer from_uid) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        UserAndCase userAndCase = caseMapper.getCaseDetail(case_id);

        if (from_uid != null) {
            UsersRelationship usersRelationship = new UsersRelationship();
            usersRelationship.setFromuid(from_uid);
            usersRelationship.setTouid(userAndCase.getUserId());

            responseMap.put("isfollowed", usersRelationshipMapper.isFollowed(usersRelationship));
        } else {
            responseMap.put("isfollowed", null); //实际为null
        }

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("blog", userAndCase);
        responseMap.put("islike", caseLikeMapper.isLiked(from_uid, case_id) == null? "false": "true");
        responseMap.put("iscollected", caseCollectionMapper.isCaseCollected(from_uid, case_id) == 0? "false": "true");

        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "发病例", notes = "创建病例")
    @ApiImplicitParam(name = "caseOfIllness", value = "CaseOfIllness实体", required = true, dataType = "CaseOfIllness")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate postMicroBlog(@ModelAttribute CaseOfIllness caseOfIllness) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

//        caseMapper.writeCaseOfIllness(caseOfIllness);
        Map<String, Object> caseOfIllnessMap = new HashMap<>();
        caseOfIllnessMap.put("user_id", caseOfIllness.getUserId());
        caseOfIllnessMap.put("title", caseOfIllness.getTitle());
        caseOfIllnessMap.put("tag_id", caseOfIllness.getTagId());
        caseOfIllnessMap.put("chief_complain", caseOfIllness.getChiefComplain());
        caseOfIllnessMap.put("chief_complain_image", caseOfIllness.getChiefComplainImage());
        caseOfIllnessMap.put("body_check", caseOfIllness.getBodyCheck());
        caseOfIllnessMap.put("body_check_image", caseOfIllness.getBodyCheckImage());
        caseOfIllnessMap.put("medical_diagnosis", caseOfIllness.getMedicalDiagnosis());
        caseOfIllnessMap.put("medical_diagnosis_image", caseOfIllness.getMedicalDiagnosisImage());
        caseOfIllnessMap.put("follow_up", caseOfIllness.getFollowUp());
        caseOfIllnessMap.put("follow_up_image", caseOfIllness.getFollowUpImage());
        caseOfIllnessMap.put("created_at", caseOfIllness.getCreatedAt());
        caseOfIllnessMap.put("comment_count", 0);
        caseOfIllnessMap.put("like_count", 0);
        caseMapper.insertCaseOfIllnessByMap(caseOfIllnessMap);

        resultDate.setCode(200);
//        responseMap.put("msg", "success");
//        responseMap.put("case", caseMapper.findCaseById(caseOfIllness.getUserId()));
        resultDate.setData("success");

        return resultDate;
    }

    /*
    测试，向兵帮忙解决中文乱码问题
    springboot默认就是utf-8，而且自带编码过滤器
    intellij默认也是utf-8,
    启动方式是war，那就往tomcat里面找问题，然后问题定位在apache-tomcat-7.0.73/conf/server.xml

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
//    @ApiOperation(value="发病例test", notes="创建病例")
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

    @ApiOperation(value = "获取某个用户的全部病例信息", notes = "根据url的user_id来获取")
    @ApiImplicitParam(name = "user_id", value = "用户自己的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public ResultDate getBlogByUserId(@PathVariable Integer user_id) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("case", caseMapper.findCaseById(user_id));
        resultDate.setData(responseMap);
        return resultDate;
    }

    @ApiOperation(value = "获取我关注的人的全部病例信息，按时间降序", notes = "根据url的user_id来获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", required = true, dataType = "int", paramType = "path"),
//            @ApiImplicitParam(name = "header", value = "用户token", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "/{user_id}/follows", method = RequestMethod.GET)
    public ResultDate getMyFollowsBlog(@PathVariable Integer user_id, @RequestHeader String Authorization) throws CustomizedException {//, @RequestHeader String header

        if (user_id == null)
            throw new CustomizedException("user_id不可为空");

//        if (!userInfoMapper.getTokenId(user_id).equals(Authorization))
//            throw new CustomizedException("您无权限访问");
//        Authorization = "Bearer " + Authorization;

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        List<Integer> followIds = usersRelationshipMapper.getMyFollowIds(user_id);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("follows", followIds);
        responseMap.put("followsCase", followIds.size()!=0 ? caseMapper.getFollowsCaseByListId(followIds) : followIds); //followIds 为null，显示[]
        resultDate.setData(responseMap);
        return resultDate;
    }

}
