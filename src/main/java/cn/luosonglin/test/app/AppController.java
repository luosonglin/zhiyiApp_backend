package cn.luosonglin.test.app;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.member.entity.UserInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 10/03/2017.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/app")
public class AppController {

    @Autowired
    UserInfoMapper userInfoMapper;

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
}
