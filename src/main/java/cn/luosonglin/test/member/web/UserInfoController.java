package cn.luosonglin.test.member.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.base.util.PhoneUtil;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.member.dao.VerificationCodeMapper;
import cn.luosonglin.test.member.entity.LoginUser;
import cn.luosonglin.test.member.entity.UserInfo;
import cn.luosonglin.test.member.entity.VerificationCode;
import cn.luosonglin.test.sms.service.SendCommonMessageService;
import cn.luosonglin.test.util.DateUtil;
import cn.luosonglin.test.util.RandUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static cn.luosonglin.test.util.RandUtil.array;

/**
 * Created by luosonglin on 25/11/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/userinfos")
// 通过这里配置使下面的映射都在/users下，可去除
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Autowired
    private SendCommonMessageService sendCommonMessageService;

    @ApiOperation(value = "手机号获取验证码", notes = "手机号获取验证码")
    @ApiImplicitParam(name = "phone", value = "用户phone", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/{phone}", method = RequestMethod.GET)
    private ResultDate registerUser(@PathVariable String phone) throws CustomizedException { //   @RequestParam("phone")

        if (phone == null)
            throw new CustomizedException("手机号不能为空");

        if (!PhoneUtil.isMobile(phone))
            throw new CustomizedException("请填写正确的手机号");

        //生成验证码
        String codeMessage = RandUtil.rand(4, array);

        VerificationCode verCode = new VerificationCode();
        verCode.setPhone(phone);
        verCode.setCodeContent(codeMessage);
        verCode.setSendDate(DateUtil.getNow());

        //检查手机号是否存在验证码表中
        VerificationCode verCodeInfo = verificationCodeMapper.getVerificationCodeByPhone(phone);


        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        if (verCodeInfo == null) {
            //借陈Mindy 的测试
//            verificationCodeMapper.insertVerificationCode(verCode);

            //插入ver_code表
            map.put("phone", verCode.getPhone());
            map.put("send_date", new Date());
            map.put("send_time", null);
            map.put("code_content", verCode.getCodeContent());
            verificationCodeMapper.insertVerificationCodeByMap(map);                    // ver_code表 code_content无记录

        } else {
//            verificationCodeMapper.updateVerificationCode(verCode);

            //更新ver_code表
            map.put("id", verCodeInfo.getId());
            map.put("phone", verCode.getPhone());
            map.put("send_date", new Date());
            map.put("send_time", null);
            map.put("code_content", codeMessage);
            verificationCodeMapper.updateVerificationCodeByMap(map);
        }


        try {
            //发送验证码
//        String result = commSendMessageAction.toSendMessage(phone , "yzm"  ,codeMessage);
            String result = sendCommonMessageService.sendCommonMessage(phone, codeMessage, "yzm");


            if ("-1".equals(result)) {
                throw new CustomizedException("用户名或者密码不正确或用户禁用或者是管理账户");
            } else if ("0".equals(result)) {
                throw new CustomizedException("0发送短信失败");   //xxxxxxxx代表消息编号
            } else if ("1".equals(result)) {    //1代表发送短信成功,xxxxxxxx代表消息编号（消息ID,在匹配状态报告时会用到）
                resultDate.setCode(200);
                responseMap.put("msg", "success");
                resultDate.setData(responseMap);
            } else if ("2".equals(result)) {
                throw new CustomizedException("余额不够或扣费错误");
            } else if ("3".equals(result)) {
                throw new CustomizedException("扣费失败异常（请联系客服）");
            } else if ("5".equals(result)) {
                throw new CustomizedException("短信定时成功");
            } else if ("6".equals(result)) {
                throw new CustomizedException("有效号码为空");
            } else if ("7".equals(result)) {
                throw new CustomizedException("短信内容为空");
            } else if ("8".equals(result)) {
                throw new CustomizedException("无签名");   //必须，格式：【签名】
            } else if ("9".equals(result)) {
                throw new CustomizedException("没有Url提交权限");
            } else if ("10".equals(result)) {
                throw new CustomizedException("发送号码过多");    //最多支持2000个号码；
            } else if ("11".equals(result)) {
                throw new CustomizedException("产品ID异常或产品禁用");
            } else if ("12".equals(result)) {
                throw new CustomizedException("参数异常");
            } else if ("13".equals(result)) {
                throw new CustomizedException("12小时内重复提交");
            } else if ("14".equals(result)) {
                throw new CustomizedException("sms异常，需联系客服人员");  //用户名或密码不正确，产品余额为0，禁止提交，联系客服
            } else if ("15".equals(result)) {
                throw new CustomizedException("Ip验证失败");
            } else if ("19".equals(result)) {
                throw new CustomizedException("短信内容过长");    //最多支持500个,或提交编码异常导致
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultDate;
    }


    @ApiOperation(value = "手机号+密码／验证码登录", notes = "用户登录")
    @ApiImplicitParam(name = "loginUser", value = "用户详细实体user", required = true, dataType = "LoginUser")
    @RequestMapping(value = "/", method = RequestMethod.POST)   //params = "grantType=code"
    private ResultDate loginByUserPhone(@ModelAttribute LoginUser loginUser) throws CustomizedException {

        if (loginUser.getPhone() == null)
            throw new CustomizedException("手机号不能为空");

        if (!PhoneUtil.isMobile(loginUser.getPhone()))
            throw new CustomizedException("请填写正确的手机号");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        //判断验证码是否合法（包括正确与否，失效与否两方面验证）
        VerificationCode verificationCode = verificationCodeMapper.getVerificationCodeByPhone(loginUser.getPhone());

        if (verificationCode == null)
            throw new CustomizedException("请先获取验证码");

        //验证用户输入的验证码和数据库ver_code表中保寸的验证码是否一致
        if (loginUser.getCode().equals(verificationCode.getCodeContent())) {

            //超过10分钟失效了
            //上线时把此处放开，测试阶段不设置短信验证码失效的问题
//			if(new Date().getTime()-verificationCode.getSendDate().getTime() > 600000)
//			    throw new CustomizedException("超过10分钟，请重新获取验证码");

            //根据用户手机号去查询用户是否已经是注册用户
            Integer userId = userInfoMapper.isRegisteredUser(loginUser.getPhone());

            UserInfo mUserInfo = new UserInfo();

            //判断账号是否已经注册过了并存在于user_info表中
            if (userId == null) {
//                Map<String, Object> map = new HashMap<>();
//
//                //什么鬼！自定义的鉴权机制？？后期必定添加OAuth2框架！！！！！！
//                //创建一个6位默认的字符串用于默认昵称后面的字符和签到确认码字符串
//                map.put("token_id", String.valueOf(UUID.randomUUID()));
//                map.put("nick_name", "医宝" + RandUtil.rand(6, array));
//                map.put("mobile_phone", loginUser.getPhone());
//                map.put("user_type", "1");
//                map.put("authen_status", "X");
//                map.put("status", "A");
//                map.put("confirm_number", RandUtil.rand(6, array));
//                map.put("state_date", new Date());
//                map.put("user_pic", "defaultPic");
//                userInfoMapper.insertUserInfoByMap(map);

                //换xml注入方式解决，真是日！
                mUserInfo.setTokenId(String.valueOf(UUID.randomUUID()));
                mUserInfo.setNickName("医宝" + RandUtil.rand(6, array));
                mUserInfo.setMobilePhone(loginUser.getPhone());
                mUserInfo.setUserType("1");
                mUserInfo.setAuthenStatus("X");
                mUserInfo.setStatus("A");
                mUserInfo.setConfirmNumber(RandUtil.rand(6, array));
                mUserInfo.setStateDate(new Date());
                mUserInfo.setUserPic("defaultPic");

                userInfoMapper.insertNewUser(mUserInfo);

            }
            System.out.println(mUserInfo != null? mUserInfo.getId(): "null");

            responseMap.put("user", userInfoMapper.getUserInfoByPhone(loginUser.getPhone()));

            resultDate.setCode(200);
            resultDate.setData(responseMap);

        } else if (verificationCode == null) {
            throw new CustomizedException("请先获取验证码");
        } else {
            throw new CustomizedException("验证码错误");
        }

        return resultDate;
    }

}
