package cn.luosonglin.test.banner.web;

import cn.luosonglin.test.banner.dao.BannerMapper;
import cn.luosonglin.test.banner.dao.EventBannerMapper;
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
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/banner")
public class BannerController {

    @Autowired
    private BannerMapper bannerMapper;

    @ApiOperation(value="获取首页幻灯片", notes="活动banner")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResultDate getBannerList() {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("banners", bannerMapper.getAllBanner());
        resultDate.setData(responseMap);

        return resultDate;
    }

    @Autowired
    EventBannerMapper eventBannerMapper;

    @ApiOperation(value="获取会议幻灯片", notes="会议banner")
    @RequestMapping(value="/meeting", method= RequestMethod.GET)
    public ResultDate getEventBannerList() {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("banners", eventBannerMapper.getAllEventBanner());
        resultDate.setData(responseMap);

        return resultDate;
    }
}
