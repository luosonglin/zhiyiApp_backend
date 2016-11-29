package cn.luosonglin.test.relationship.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.relationship.dao.UsersRelationshipMapper;
import cn.luosonglin.test.relationship.entity.UsersRelationship;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 29/11/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/relation")
public class UsersRelationshipController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UsersRelationshipMapper usersRelationshipMapper;

    @ApiOperation(value="获取所有潜在关注对象的列表", notes="")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultDate getUsersInfo() {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("users", usersRelationshipMapper.findAllUser());
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="加关注", notes="根据Relationship对象创建关注关系")
    @ApiImplicitParam(name = "usersRelationship", value = "关系详细实体usersRelationship", required = true, dataType = "UsersRelationship")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate follow(@ModelAttribute UsersRelationship usersRelationship) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        usersRelationshipMapper.insertByRelationShip(usersRelationship);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="取消关注", notes="根据关系双方的id来指定删除关系对象")
    @ApiImplicitParam(name = "usersRelationship", value = "关系详细实体usersRelationship", required = true, dataType = "UsersRelationship")
    @RequestMapping(value="/", method=RequestMethod.DELETE)
    public ResultDate deleteUser(@ModelAttribute UsersRelationship usersRelationship) {
        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        usersRelationshipMapper.deleteByRelationShip(usersRelationship);

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }

}
