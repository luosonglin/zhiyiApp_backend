package cn.luosonglin.test.relationship.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.member.entity.UserInfo;
import cn.luosonglin.test.relationship.dao.UsersRelationshipMapper;
import cn.luosonglin.test.relationship.entity.UsersRelationship;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ResultDate follow(@ModelAttribute UsersRelationship usersRelationship) throws CustomizedException {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        //判断对方是否是已关注对象
        if (usersRelationshipMapper.isFollowed(usersRelationship) != 0)
            throw new CustomizedException("不可重复关注同一用户");

        //判断是否是自己关注自己
        if (usersRelationship.getFromuid() == usersRelationship.getTouid())
            throw new CustomizedException("不可自己关注自己");

        //关系表插入新记录
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
        Map<String, Object> responseMap = new HashMap<>();

        usersRelationshipMapper.deleteByRelationShip(usersRelationship);

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value="获取我的粉丝信息", notes="根据user_id来获取我的粉丝详细信息")
    @ApiImplicitParam(name = "id", value = "用户自己的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{id}/fanInfo", method=RequestMethod.GET)
    public ResultDate getMyFans(@PathVariable Integer id) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        //获取关注我的所有用户信息
        List<UsersRelationship> fans = usersRelationshipMapper.getMyFans(id);

        //获取关注我的所有用户id
        List<Integer> fanIds = new ArrayList<>();
        for (UsersRelationship i: fans)
            fanIds.add(i.getFromuid());

        //获取所有关注我的用户详细信息
        List<UserInfo> fanInfos = new ArrayList<>();
        for (Integer u : fanIds)
            fanInfos.add(userInfoMapper.getUserInfoByUserId(u));

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("fans", fanInfos);
        resultDate.setData(responseMap);
        return resultDate;
    }

    @ApiOperation(value="获取我的粉丝数量", notes="根据user_id来获取我的粉丝数量")
    @ApiImplicitParam(name = "id", value = "用户自己的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{id}/fans", method=RequestMethod.GET)
    public ResultDate getMyFanCount(@PathVariable Integer id) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("fans", usersRelationshipMapper.getFansCount(id));
        resultDate.setData(responseMap);
        return resultDate;
    }

    @ApiOperation(value="获取我的关注数量", notes="根据user_id来获取我的关注数量")
    @ApiImplicitParam(name = "id", value = "用户自己的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{id}/follows", method=RequestMethod.GET)
    public ResultDate getMyFollowCount(@PathVariable Integer id) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("follows", usersRelationshipMapper.getFollowedCount(id));
        resultDate.setData(responseMap);
        return resultDate;
    }

    @ApiOperation(value="获取我关注的人的信息", notes="根据user_id来获取我的关注人详细信息")
    @ApiImplicitParam(name = "id", value = "用户自己的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{id}/followInfo", method=RequestMethod.GET)
    public ResultDate getMyFollowInfo(@PathVariable Integer id) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        //获取我关注的所有用户信息
        List<UsersRelationship> follows = usersRelationshipMapper.getMyFollows(id);

        //获取我关注的所有用户id
        List<Integer> followIds = new ArrayList<>();
        for (UsersRelationship i: follows)
            followIds.add(i.getTouid());

        //获取所有我关注的用户详细信息
        List<UserInfo> followInfos = new ArrayList<>();
        for (Integer u : followIds)
            followInfos.add(userInfoMapper.getUserInfoByUserId(u));

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("follows", followInfos);
        resultDate.setData(responseMap);
        return resultDate;
    }

    @ApiOperation(value="获取通讯录(我关注的人)的信息,并按中文首字母排序", notes="根据user_id来获取我的关注人详细信息")
    @ApiImplicitParam(name = "id", value = "用户自己的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{id}/contactsInfo", method=RequestMethod.GET)
    public ResultDate getMycontactsInfo(@PathVariable Integer id) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        //获取我关注的所有用户信息
        List<UsersRelationship> follows = usersRelationshipMapper.getMyFollows(id);

        //获取我关注的所有用户id
        List<Integer> followIds = new ArrayList<>();
        for (UsersRelationship i: follows)
            followIds.add(i.getTouid());

        //获取所有我关注的用户详细信息
//        List<UserInfo> followInfos = new ArrayList<>();
//        for (Integer u : followIds)
//            followInfos.add(userInfoMapper.getUserInfoByUserId(u));

        resultDate.setCode(200);
        responseMap.put("msg", "success");
//        responseMap.put("follows", followInfos);
        responseMap.put("test", followIds);
        responseMap.put("contacts", usersRelationshipMapper.getContactsByListId(followIds));
        resultDate.setData(responseMap);
        return resultDate;
    }
}
