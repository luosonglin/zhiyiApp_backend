package cn.luosonglin.test.android.web;

/**
 * Created by luosonglin on 31/08/2017.
 */

import cn.luosonglin.test.android.dao.AndroidPhoneInfoMapper;
import cn.luosonglin.test.android.dao.AndroidVersionLogMapper;
import cn.luosonglin.test.android.dao.AndroidVersionMapper;
import cn.luosonglin.test.android.entity.AndroidPhoneInfo;
import cn.luosonglin.test.android.entity.AndroidVersionLog;
import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.entity.Blog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/android_version")
public class AndroidVersionController {
    @Autowired
    AndroidVersionMapper androidVersionMapper;

    @Autowired
    AndroidVersionLogMapper androidVersionLogMapper;

    @Autowired
    AndroidPhoneInfoMapper androidPhoneInfoMapper;

    @ApiOperation(value = "获取Android version", notes = "")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultDate getLastAndroidVersion() {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();
        resultDate.setCode(200);
        responseMap.put("version", androidVersionMapper.getLastVersion());
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "添加新的一条记录", notes = "")
    @ApiImplicitParam(name = "AndroidVersionLog", value = "AndroidVersionLog实体", required = true, dataType = "AndroidVersionLog")
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public ResultDate addNewLog(@ModelAttribute AndroidVersionLog androidVersionLog) {
        ResultDate resultDate = new ResultDate();

        androidVersionLogMapper.insertByNewVersion(androidVersionLog.getUserId(), androidVersionLog.getOldVersionId(), androidVersionLog.getNewVersionId());

        resultDate.setCode(200);
        resultDate.setData("success");

        return resultDate;
    }

    @ApiOperation(value = "手机信息", notes = "")
    @ApiImplicitParam(name = "AndroidPhoneInfo", value = "AndroidPhoneInfo实体", required = true, dataType = "AndroidPhoneInfo")
    @RequestMapping(value = "/phoneInfo", method = RequestMethod.POST)
    public ResultDate addNewPhoneInfo(@ModelAttribute AndroidPhoneInfo androidPhoneInfo) {
        ResultDate resultDate = new ResultDate();


        androidPhoneInfoMapper.insertByNewPhoneInfo(androidPhoneInfo.getUserId(), androidPhoneInfo.getUniqueSerialNumber(),
                androidPhoneInfo.getMetrics(),
                androidPhoneInfo.getImei(),
                androidPhoneInfo.getImsi(),
                androidPhoneInfo.getMacAddress(),
                androidPhoneInfo.getMccMnc(),
                androidPhoneInfo.getSimOperatorName(),
                androidPhoneInfo.getIsRoot());

        resultDate.setCode(200);
        resultDate.setData("success");

        return resultDate;
    }
}
