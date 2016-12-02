package cn.luosonglin.test.activities.web;

import cn.luosonglin.test.activities.dao.ActivitiesMapper;
import cn.luosonglin.test.base.entity.ResultDate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 01/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/activity")
public class ActivitiesController {
    @Autowired
    private ActivitiesMapper activitiesMapper;

    @ApiOperation(value="获取活动推送广告位", notes="活动")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResultDate getActivitiesList() {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("banners", activitiesMapper.getAllActivities());
        resultDate.setData(responseMap);

        return resultDate;
    }
}
