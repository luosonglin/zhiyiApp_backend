package cn.luosonglin.test.qiniu.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.qiniu.config.Config;
import com.qiniu.util.Auth;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 23/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/qiniu")
public class QiniuTokenController {
    @ApiOperation(value="获取七牛云存储的某个bucket的uploadToken", notes="获取七牛云存储的uploadToken")
    @ApiImplicitParam(name = "bucketName", value = "测试的空间", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/{bucketName}/", method=RequestMethod.GET)
    public ResultDate get云存储的uploadToken(@PathVariable String bucketName) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        Auth auth = Auth.create(Config.ACCESS_KEY, Config.SECRET_KEY);
        String token = auth.uploadToken(bucketName);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("uploadToken",token);
        resultDate.setData(responseMap);

        return resultDate;
    }
}
