package cn.luosonglin.test.easemob.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.base.util.DateUtil;
import cn.luosonglin.test.base.util.PhoneUtil;
import cn.luosonglin.test.base.util.RandUtil;
import cn.luosonglin.test.easemob.service.ChatService;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.entity.UserInfo;
import cn.luosonglin.test.member.entity.VerificationCode;
import cn.luosonglin.test.relationship.entity.UsersRelationship;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by luosonglin on 22/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;
/*
    @ApiOperation(value = "通讯录好友列表", notes = "获取通讯录好友列表")
    @ApiImplicitParam(name = "user_id", value = "用户id", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    private ResultDate registerUser(@PathVariable String user_id) {

        chatService.getFriendInfoService(user_id);

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


        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();



        return resultDate;
    }*/

}
