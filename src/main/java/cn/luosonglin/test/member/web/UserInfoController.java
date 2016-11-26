package cn.luosonglin.test.member.web;

import cn.luosonglin.test.base.ResultDate;
import cn.luosonglin.test.base.util.PhoneUtil;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.member.dao.VerificationCodeMapper;
import cn.luosonglin.test.member.entity.VerificationCode;
import cn.luosonglin.test.sms.service.SendCommonMessageService;
import cn.luosonglin.test.util.DateUtil;
import cn.luosonglin.test.util.RandUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 25/11/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/users1")
// 通过这里配置使下面的映射都在/users下，可去除
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Autowired
    private SendCommonMessageService sendCommonMessageService;

    @ApiOperation(value = "手机号登录", notes = "手机号获取验证码登录")
    @ApiImplicitParam(name = "phone", value = "用户phone", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    private VerificationCode registerUser(@ModelAttribute String phone) throws CustomizedException {


        if (phone == null)
            throw new CustomizedException("手机号不能为空");

        if (PhoneUtil.isMobile(phone))
            throw new CustomizedException("请填写正确的手机号");

        //生成验证码
        String codeMessage = RandUtil.rand(4);

        VerificationCode verCode = new VerificationCode();
        verCode.setPhone(phone);
        verCode.setCodeContent(codeMessage);
        verCode.setSendDate(DateUtil.getNow());

        //检查手机号是否存在验证码表中
        VerificationCode verCodeInfo = null;//verificationCodeMapper.getVerificationCodeByPhone(phone);

//        if (verCodeInfo == null) {
//            verificationCodeMapper.insertVerificationCode(verCode);
//        } else {
//            verificationCodeMapper.updateVerificationCode(verCode);
//        }
//
//        ResultDate resultDate = new ResultDate();
//        Map<Object, Object> responseMap = new HashMap<>();
//
//        try {
//            //发送验证码
////        String result = commSendMessageAction.toSendMessage(phone , "yzm"  ,codeMessage);
//            String result = sendCommonMessageService.sendCommonMessage(phone, codeMessage, "yzm");
//
//
//            if ("-1".equals(result)) {
//                throw new CustomizedException("用户名或者密码不正确或用户禁用或者是管理账户");
//            } else if ("0".equals(result)) {
//                throw new CustomizedException("0发送短信失败");   //xxxxxxxx代表消息编号
//            } else if ("1".equals(result)) {    //1代表发送短信成功,xxxxxxxx代表消息编号（消息ID,在匹配状态报告时会用到）
//                resultDate.setCode(200);
//                responseMap.put("msg", "success");
//                resultDate.setData(responseMap);
//            } else if ("2".equals(result)) {
//                throw new CustomizedException("余额不够或扣费错误");
//            } else if ("3".equals(result)) {
//                throw new CustomizedException("扣费失败异常（请联系客服）");
//            } else if ("5".equals(result)) {
//                throw new CustomizedException("短信定时成功");
//            } else if ("6".equals(result)) {
//                throw new CustomizedException("有效号码为空");
//            } else if ("7".equals(result)) {
//                throw new CustomizedException("短信内容为空");
//            } else if ("8".equals(result)) {
//                throw new CustomizedException("无签名");   //必须，格式：【签名】
//            } else if ("9".equals(result)) {
//                throw new CustomizedException("没有Url提交权限");
//            } else if ("10".equals(result)) {
//                throw new CustomizedException("发送号码过多");    //最多支持2000个号码；
//            } else if ("11".equals(result)) {
//                throw new CustomizedException("产品ID异常或产品禁用");
//            } else if ("12".equals(result)) {
//                throw new CustomizedException("参数异常");
//            } else if ("15".equals(result)) {
//                throw new CustomizedException("Ip验证失败");
//            } else if ("19".equals(result)) {
//                throw new CustomizedException("短信内容过长");    //最多支持500个,或提交编码异常导致
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        return resultDate;
        return verCodeInfo;
    }

}
