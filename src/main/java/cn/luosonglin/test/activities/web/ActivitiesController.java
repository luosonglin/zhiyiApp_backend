package cn.luosonglin.test.activities.web;

import cn.luosonglin.test.activities.dao.ActivitiesMapper;
import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.base.util.MD5Gen;
import cn.luosonglin.test.easemob.service.ChatService;
import cn.luosonglin.test.exception.CustomizedException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static cn.luosonglin.test.easemob.api.EasemobIMUsers.createNewIMUserSingle;

/**
 * Created by luosonglin on 01/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/activity")
public class ActivitiesController {
    @Autowired
    private ActivitiesMapper activitiesMapper;

    @Autowired
    private ChatService chatService;

    @ApiOperation(value="获取活动推送广告位", notes="活动")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResultDate getActivitiesList() throws CustomizedException {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

//        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
//        datanode.put("username","2");
//        datanode.put("password", MD5Gen.getMD5("123456"));
//        ObjectNode createNewIMUserSingleNode = createNewIMUserSingle(datanode);

        resultDate.setCode(200);
        responseMap.put("banners", activitiesMapper.getAllActivities());
//        responseMap.put("imuser", createNewIMUserSingleNode);

//        chatService.createNewIMUser("luo", "123")
        resultDate.setData(responseMap);

        return resultDate;
    }
}
