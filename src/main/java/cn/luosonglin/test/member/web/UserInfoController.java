package cn.luosonglin.test.member.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.base.util.PhoneUtil;
import cn.luosonglin.test.easemob.service.ChatService;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.dao.AuthenMapper;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.member.dao.VerificationCodeMapper;
import cn.luosonglin.test.member.entity.*;
import cn.luosonglin.test.relationship.dao.UsersRelationshipMapper;
import cn.luosonglin.test.sms.service.SendCommonMessageService;
import cn.luosonglin.test.base.util.DateUtil;
import cn.luosonglin.test.base.util.RandUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static cn.luosonglin.test.base.util.RandUtil.array;

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

    @Autowired
    private ChatService chatService;

    @Autowired
    private UsersRelationshipMapper usersRelationshipMapper;

    @Autowired
    private AuthenMapper authenMapper;

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
            map.put("id", null);
            map.put("phone", verCode.getPhone());
            map.put("send_date", new Date());
            map.put("send_time", null);
            map.put("code_content", codeMessage);   //verCode.getCodeContent()
            verificationCodeMapper.insertVerificationCodeByMap(map);

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

    @ApiOperation(value = "test", notes = "test")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    private String test() throws CustomizedException {
        String confirmNumber = "708395";
        String number = userInfoMapper.isConfirmNumberExists(confirmNumber);
        System.out.print(number + " hhh");
        while (number != null) {
            confirmNumber = RandUtil.rand(6, array);
            number = userInfoMapper.isConfirmNumberExists(confirmNumber);
            if (number == null) break;
        }

        return confirmNumber + number;
    }

    @ApiOperation(value = "手机号+验证码登录", notes = "用户登录")
    @ApiImplicitParam(name = "loginUserByCode", value = "用户详细实体user", required = true, dataType = "LoginUserByCode")
    @RequestMapping(value = "/", method = RequestMethod.POST)   //params = "grantType=code"
    private ResultDate loginByUserPhone(@ModelAttribute LoginUserByCode loginUserByCode) throws CustomizedException {

        if (loginUserByCode.getPhone() == null)
            throw new CustomizedException("手机号不能为空");

        if (loginUserByCode.getCode() == null)
            throw new CustomizedException("验证码不能为空");

        if (!PhoneUtil.isMobile(loginUserByCode.getPhone()))
            throw new CustomizedException("请填写正确的手机号");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        //判断验证码是否合法（包括正确与否，失效与否两方面验证）
        VerificationCode verificationCode = verificationCodeMapper.getVerificationCodeByPhone(loginUserByCode.getPhone());

        if (verificationCode == null)
            throw new CustomizedException("请先获取验证码");

        //验证用户输入的验证码和数据库ver_code表中保寸的验证码是否一致
        if (loginUserByCode.getCode().equals(verificationCode.getCodeContent())) {

            //超过10分钟失效了
            //上线时把此处放开，测试阶段不设置短信验证码失效的问题
//			if(new Date().getTime()-verificationCode.getSendDate().getTime() > 600000)
//			    throw new CustomizedException("超过10分钟，请重新获取验证码");

            //根据用户手机号去查询用户是否已经是注册用户
            /*
             *   此处数据库user_info表的mobile_phone加上唯一性约束
             *   ALTER TABLE `user_info` ADD unique(`mobile_phone`);
             */
            Integer userId = userInfoMapper.isRegisteredUser(loginUserByCode.getPhone());

            System.out.println("userId" + userId);

            UserInfo mUserInfo = new UserInfo();

            //判断账号是否已经注册过了并存在于user_info表中
            if (userId == null) {
//                Map<String, Object> map = new HashMap<>();
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
                mUserInfo.setMobilePhone(loginUserByCode.getPhone());
                mUserInfo.setUserType("1");
                mUserInfo.setAuthenStatus("X");
                mUserInfo.setStatus("A");

                String confirmNumber = RandUtil.rand(6, array);
                String number = userInfoMapper.isConfirmNumberExists(confirmNumber);
                System.out.print(number + " hhh");
                while (number != null) {
                    confirmNumber = RandUtil.rand(6, array);
                    number = userInfoMapper.isConfirmNumberExists(confirmNumber);
                    if (number == null) break;
                }
                mUserInfo.setConfirmNumber(confirmNumber);

                mUserInfo.setStateDate(new Date());
                mUserInfo.setUserPic("defaultPic");

//                userInfoMapper.insertNewUser(mUserInfo);
                userInfoMapper.insertByUserInfo(mUserInfo);

                //在环信服务器注册新用户
                Integer user_id = userInfoMapper.getMaxUserId();
//                chatService.createNewIMUserService(Integer.toString(user_id), loginUserByCode.getCode());
                //所有用户密码为luosonglin123456的md5值
                chatService.createNewIMUserService(Integer.toString(user_id), "123456");//MD5Gen.getMD5("luosonglin123456")


                //关系表插入新记录 7为新注册即可关注的对象，就是我哈哈哈哈哈哈
                //等客户端加及时推送再开放
//                usersRelationshipMapper.insertByRelationShip(new UsersRelationship(user_id, 7));
//                chatService.addFriendSingleService(Integer.toString(user_id), Integer.toString(7));

            } else {
                responseMap.put("chat", chatService.modifyIMUserPasswordService(Integer.toString(userId), loginUserByCode.getCode()));
            }

            System.out.println(mUserInfo != null ? mUserInfo.getId() : "null");

            responseMap.put("user", userInfoMapper.getUserInfoByPhone(loginUserByCode.getPhone()));

            resultDate.setCode(200);
            resultDate.setData(responseMap);

        } else if (verificationCode == null) {
            throw new CustomizedException("请先获取验证码");
        } else {
            throw new CustomizedException("验证码错误");
        }

        return resultDate;
    }

    @ApiOperation(value = "手机号+密码登录", notes = "用户登录")
    @ApiImplicitParam(name = "loginUserByPassword", value = "用户详细实体user", required = true, dataType = "LoginUserByPassword")
    @RequestMapping(value = "/pwd", method = RequestMethod.POST)   //params = "grantType=code"
    private ResultDate loginByUserPassword(@ModelAttribute LoginUserByPassword loginUserByPassword) throws CustomizedException {
        if (loginUserByPassword.getPhone() == null)
            throw new CustomizedException("手机号不能为空");

        if (loginUserByPassword.getPassword() == null)
            throw new CustomizedException("密码不能为空");

        if (!PhoneUtil.isMobile(loginUserByPassword.getPhone()))
            throw new CustomizedException("请填写正确的手机号");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        //根据用户手机号去查询用户是否已经是注册用户
        Integer userId = userInfoMapper.isRegisteredUser(loginUserByPassword.getPhone());

        UserInfo mUserInfo = new UserInfo();

        //判断账号是否已经注册过了并存在于user_info表中
        if (userId == null)
            throw new CustomizedException("没有该用户，请先获取验证码注册");

        if (!loginUserByPassword.getPassword().equals(userInfoMapper.getUserInfoByPhone(loginUserByPassword.getPhone()).getPassword()))
            throw new CustomizedException("密码错误");

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        responseMap.put("user", userInfoMapper.getUserInfoByUserId(userId));

        //环信的所有用户密码为MD5Gen.getMD5("luosonglin123456")
//        responseMap.put("chat", chatService.modifyIMUserPasswordService(Integer.toString(userId), loginUserByPassword.getPassword()));

//        responseMap.put("chat", chatService.imUserLoginService(Integer.toString(userId), loginUser.getPassword()));
        resultDate.setData(responseMap);

        return resultDate;

    }


    @ApiOperation(value = "认证医师", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParam(name = "userInfo", value = "用户详细实体user", required = true, dataType = "UserInfo")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResultDate authoruzationUser(@ModelAttribute UserInfo userInfo) throws CustomizedException {

//        UserInfo u = userInfoMapper.getUserInfoByUserId(userInfo.getId());
//        u.setName(userInfo.getName());
//        u.setCompany(userInfo.getCompany());
//        u.setPostion(userInfo.getPostion());
//        u.setTitle(userInfo.getTitle());
//        u.setAuthenStatus("B"); //认证状态(''A:已认证'',''B:待认证''''X:未认证'')
//        userInfoMapper.update(u);

        if (!PhoneUtil.isMobile(userInfo.getMobilePhone()))
            throw new CustomizedException("请填写正确的手机号");

//        //该用户是否已用该手机号注册过
//        Integer userId = userInfoMapper.isRegisteredUser(userInfo.getMobilePhone());
//        if (userId != null) {
//            throw new CustomizedException("该手机号已经被注册过");
//        }
//
//        String phone = userInfoMapper.getUserInfoMobilePhone(userInfo.getId());
//        if (phone.equals(userInfo.getMobilePhone())) {
//            throw new CustomizedException("该手机号已经被其他用户绑定");
//        }

        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("id", userInfo.getId());
        userInfoMap.put("name", userInfo.getName());
        userInfoMap.put("company", userInfo.getCompany());
        //数据库user_info表中，title职称  position职位
//        userInfoMap.put("position", userInfo.getPostion());
        userInfoMap.put("department", userInfo.getDepartment());
        userInfoMap.put("hospital", userInfo.getHospital());
        userInfoMap.put("title", userInfo.getTitle());
        userInfoMap.put("mobile_phone", userInfo.getMobilePhone());
        userInfoMap.put("authen_status", "B");

        userInfoMapper.authorization(userInfoMap);

        //认证用户
        //现在需要将用户的认证申请的信息再存在authen表，尴尬
        Authen authen = new Authen();
        authen.setUserId(userInfo.getId());
        authen.setUserType("A");
        authen.setAuthenStatus("B");
        authen.setStateDate(new Date());
        authen.setMobliePhone(userInfo.getMobilePhone());
        authen.setUserName(userInfo.getName());
        authen.setUserHospital(userInfo.getHospital());
        authen.setUserDepartment(userInfo.getDepartment());
        authen.setUserTitle(userInfo.getTitle());
        authenMapper.insertAuthen(authen);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        responseMap.put("user", userInfoMapper.getUserInfoByUserId(userInfo.getId()));
        resultDate.setData(responseMap);

        return resultDate;
    }


  /*  @ApiOperation(value = "修改头像／手机号(以后可以更改用户的任意字段)", notes = "根据id来指定更新用户头像(id,userPic)(id,mobilePhone)")
    @ApiImplicitParam(name = "userInfo", value = "UserInfo实体", required = true, dataType = "UserInfo")
    @RequestMapping(value = "/avatar", method = RequestMethod.PUT)
    public ResultDate updateAvatar(@ModelAttribute UserInfo userInfo) throws CustomizedException {
//        Map<String, Object> userInfoMap = new HashMap<>();
//        userInfoMap.put("user_id", userInfo.getId());

//        if (userInfo.getUserPic() != null) {
//            userInfoMap.put("user_pic", userInfo.getUserPic());
//        } else if (userInfo.getMobilePhone() != null) {
//            userInfoMap.put("mobile_phone", userInfo.getMobilePhone());
//        }

        if (!PhoneUtil.isMobile(userInfo.getMobilePhone()))
            throw new CustomizedException("请填写正确的手机号");

//        userInfoMapper.updateUserPic(userInfoMap);
        userInfoMapper.update(userInfo);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("user", userInfoMapper.getUserInfoByUserId(userInfo.getId()));
        resultDate.setData(responseMap);

        return resultDate;
    }*/

    @ApiOperation(value = "修改头像", notes = "根据id来指定更新用户头像(id,avatar)")
    @ApiImplicitParam(name = "updateUserAvatar", value = "UpdateUserAvatar实体", required = true, dataType = "UpdateUserAvatar")
    @RequestMapping(value = "/avatar", method = RequestMethod.PUT)
    public ResultDate updateAvatar(@ModelAttribute UpdateUserAvatar updateUserAvatar) throws CustomizedException {
        userInfoMapper.updateUserAvatar(updateUserAvatar);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("user", userInfoMapper.getUserInfoByUserId(updateUserAvatar.getUserId()));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "修改手机号", notes = "根据id来指定更新用户手机号(id,mobilePhone)")
    @ApiImplicitParam(name = "updateUserPhone", value = "UpdateUserPhone实体", required = true, dataType = "UpdateUserPhone")
    @RequestMapping(value = "/phone", method = RequestMethod.PUT)
    public ResultDate updateAvatar(@ModelAttribute UpdateUserPhone updateUserPhone) throws CustomizedException {

        if (!PhoneUtil.isMobile(updateUserPhone.getMobilePhone()))
            throw new CustomizedException("请填写正确的手机号");

        userInfoMapper.updateUserPhone(updateUserPhone);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("user", userInfoMapper.getUserInfoByUserId(updateUserPhone.getUserId()));
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value = "第三方登录(changed)", notes = "用户登录")
    @ApiImplicitParam(name = "thirdUser", value = "用户详细实体thirdUser", required = true, dataType = "ThirdUser")
    @RequestMapping(value = "/third", method = RequestMethod.POST)
    private ResultDate loginByThird(@ModelAttribute ThirdUser thirdUser) throws CustomizedException {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        if (thirdUser.getPlatform().equals("WeChat")) {     // wechat登陆进来
            if (thirdUser.getOpenId() == null)
                throw new CustomizedException("WeChat openId不能为空");
        } else if (thirdUser.getPlatform().equals("QQ")){
            //此处屏蔽为了iOS第一版
//            if (thirdUser.getQqOpenId() == null)
//                throw new CustomizedException("QQ openId不能为空");
        }

        if (thirdUser.getMobilePhone() == null && thirdUser.getCode() != null) {
            throw new CustomizedException("手机号不能为空");
        } else if (thirdUser.getMobilePhone() != null &&thirdUser.getCode() == null) {
            throw new CustomizedException("验证码不能为空");
        } else if (thirdUser.getMobilePhone() == null &&thirdUser.getCode() == null) { //老版本

            UserInfo userInfo = userInfoMapper.getUserInfoByOpenId(thirdUser.getOpenId());
            if (userInfo == null) { //查看该用户是否是iOS第一版本用户
                Map<String, Object> userInfoMap = new HashMap<>();
                userInfoMap.put("nick_name", thirdUser.getNickName());
                userInfoMap.put("login_source", thirdUser.getPlatform());
                userInfoMap.put("user_pic", thirdUser.getIconurl());
                if (thirdUser.getPlatform().equals("WeChat")) {     // wechat登陆进来
                    userInfoMap.put("open_id", thirdUser.getOpenId());
                    userInfoMap.put("qq_open_id", null);
                } else if (thirdUser.getPlatform().equals("QQ")) {
                    userInfoMap.put("open_id", null);
                    userInfoMap.put("qq_open_id", thirdUser.getQqOpenId());
                }
                userInfoMap.put("token_id", String.valueOf(UUID.randomUUID()));
                userInfoMap.put("user_type", "1");
                userInfoMap.put("authen_status", "X");
                userInfoMap.put("status", "A");
                userInfoMap.put("user_source", thirdUser.getUserSource());
                String confirmNumber = RandUtil.rand(6, array);
                String number = userInfoMapper.isConfirmNumberExists(confirmNumber);
                System.out.print(number + " hhh");
                while (number != null) {
                    confirmNumber = RandUtil.rand(6, array);
                    number = userInfoMapper.isConfirmNumberExists(confirmNumber);
                    if (number == null) break;
                }
                userInfoMap.put("confirm_number", confirmNumber);

                userInfoMap.put("state_date", new Date());
                userInfoMap.put("password", "123456");//为了在环信注册，默认密码为123456

                userInfoMapper.insertUserInfoByMap(userInfoMap);

                //在环信服务器注册新用户
                chatService.createNewIMUserService(Integer.toString(userInfoMapper.getMaxUserId()), "123456");//MD5Gen.getMD5("luosonglin123456"));
            }

            resultDate.setCode(200);
            if (thirdUser.getPlatform().equals("WeChat")) {
                responseMap.put("user", userInfoMapper.getUserInfoByOpenId(thirdUser.getOpenId()));
            } else if (thirdUser.getPlatform().equals("QQ")){
                if (userInfoMapper.getUserInfoByQQOpenId(thirdUser.getOpenId()) == null && thirdUser.getQqOpenId() == null) {    //为了第一版本qq openId 存在了wechat openId
                    responseMap.put("user", userInfoMapper.getUserInfoByOpenId(thirdUser.getOpenId()));
                } else {
                    responseMap.put("user", userInfoMapper.getUserInfoByQQOpenId(thirdUser.getQqOpenId()));
                }
            }
            resultDate.setData(responseMap);

            return resultDate;
        }

        if (!PhoneUtil.isMobile(thirdUser.getMobilePhone()))
            throw new CustomizedException("请填写正确的手机号");

        //判断验证码是否合法（包括正确与否，失效与否两方面验证）
        VerificationCode verificationCode = verificationCodeMapper.getVerificationCodeByPhone(thirdUser.getMobilePhone());

        if (verificationCode == null)
            throw new CustomizedException("请先获取验证码");

        //验证用户输入的验证码和数据库ver_code表中保寸的验证码是否一致
        if (thirdUser.getCode().equals(verificationCode.getCodeContent())) {
            Integer userId = userInfoMapper.isRegisteredUser(thirdUser.getMobilePhone());

            if (userId != null) {   //手机号是旧号，被注册过
                Map<String, Object> userInfoMap = new HashMap<>();
                userInfoMap.put("nick_name", thirdUser.getNickName());
                userInfoMap.put("login_source", thirdUser.getPlatform());
                userInfoMap.put("user_pic", thirdUser.getIconurl());
                userInfoMap.put("mobile_phone", thirdUser.getMobilePhone());

                if (thirdUser.getPlatform().equals("WeChat")) {     // wechat登陆进来
                    userInfoMap.put("open_id", thirdUser.getOpenId());
                    userInfoMapper.updateUserByWeChatInfo(userInfoMap);
                } else if (thirdUser.getPlatform().equals("QQ")){
                    userInfoMap.put("qq_open_id", thirdUser.getQqOpenId());
                    userInfoMapper.updateUserByQQInfo(userInfoMap);
                }
            } else {
                //手机号是新号，则看open_id是否是新的

                UserInfo openId = userInfoMapper.getUserInfoByOpenId(thirdUser.getOpenId());
                if (openId != null) {   //openid是老用户
                    userInfoMapper.updateUserPhoneByOpenId(thirdUser.getMobilePhone(), thirdUser.getOpenId());
                } else {
                    //openid 和 phone 都是新的
                    Map<String, Object> userInfoMap = new HashMap<>();
                    userInfoMap.put("nick_name", thirdUser.getNickName());
                    userInfoMap.put("login_source", thirdUser.getPlatform());
                    userInfoMap.put("user_pic", thirdUser.getIconurl());
                    if (thirdUser.getPlatform().equals("WeChat")) {     // wechat登陆进来
                        userInfoMap.put("open_id", thirdUser.getOpenId());
                        userInfoMap.put("qq_open_id", null);
                    } else if (thirdUser.getPlatform().equals("QQ")) {
                        userInfoMap.put("open_id", null);
                        userInfoMap.put("qq_open_id", thirdUser.getQqOpenId());
                    }
                    userInfoMap.put("token_id", String.valueOf(UUID.randomUUID()));
                    userInfoMap.put("user_type", "1");
                    userInfoMap.put("authen_status", "X");
                    userInfoMap.put("status", "A");
                    userInfoMap.put("user_source", thirdUser.getUserSource());
                    userInfoMap.put("mobile_phone", thirdUser.getMobilePhone());


                    String confirmNumber = RandUtil.rand(6, array);
                    String number = userInfoMapper.isConfirmNumberExists(confirmNumber);
                    System.out.print(number + " hhh");
                    while (number != null) {
                        confirmNumber = RandUtil.rand(6, array);
                        number = userInfoMapper.isConfirmNumberExists(confirmNumber);
                        if (number == null) break;
                    }
                    userInfoMap.put("confirm_number", confirmNumber);

                    userInfoMap.put("state_date", new Date());
                    userInfoMap.put("password", "123456");//为了在环信注册，默认密码为123456

                    userInfoMapper.insertUserInfoByMap(userInfoMap);

                    //在环信服务器注册新用户
                    chatService.createNewIMUserService(Integer.toString(userInfoMapper.getMaxUserId()), "123456");//MD5Gen.getMD5("luosonglin123456"));
                }
            }
        }else if (verificationCode == null) {
            throw new CustomizedException("请先获取验证码");
        } else {
            throw new CustomizedException("验证码错误");
        }

        resultDate.setCode(200);
        responseMap.put("user", userInfoMapper.getUserInfoByPhone(thirdUser.getMobilePhone()));
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value = "三方登陆用户绑定手机号", notes = "根据id来指定添加用户手机号(id,mobilePhone)")
    @ApiImplicitParam(name = "ThirdUserBindPhoneInfo", value = "ThirdUserBindPhoneInfo实体", required = true, dataType = "ThirdUserBindPhoneInfo")
    @RequestMapping(value = "/third/phone", method = RequestMethod.PUT)
    public ResultDate insertThridUserPhone(@ModelAttribute ThirdUserBindPhoneInfo thirdUserBindPhoneInfo) throws CustomizedException {

        if (thirdUserBindPhoneInfo.getPhone() == null)
            throw new CustomizedException("手机号不能为空");

        if (thirdUserBindPhoneInfo.getCode() == null)
            throw new CustomizedException("验证码不能为空");

        if (!PhoneUtil.isMobile(thirdUserBindPhoneInfo.getPhone()))
            throw new CustomizedException("请填写正确的手机号");

        //根据用户手机号去查询用户是否已经是注册用户
        String openId = userInfoMapper.isThirdRegisteredUser(thirdUserBindPhoneInfo.getUserId());
        //判断openId是否存在于user_info表中
        if (openId == null)
            throw new CustomizedException("该用户非三方应用正常登陆");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        //判断验证码是否合法（包括正确与否，失效与否两方面验证）
        VerificationCode verificationCode = verificationCodeMapper.getVerificationCodeByPhone(thirdUserBindPhoneInfo.getPhone());

        if (verificationCode == null)
            throw new CustomizedException("请先获取验证码");

        //验证用户输入的验证码和数据库ver_code表中保寸的验证码是否一致
        if (thirdUserBindPhoneInfo.getCode().equals(verificationCode.getCodeContent())) {
            //超过10分钟失效了
            //上线时把此处放开，测试阶段不设置短信验证码失效的问题
//			if(new Date().getTime()-verificationCode.getSendDate().getTime() > 600000)
//			    throw new CustomizedException("超过10分钟，请重新获取验证码");

            //该用户是否已用该手机号注册过
            Integer userId = userInfoMapper.isRegisteredUser(thirdUserBindPhoneInfo.getPhone());
            if (userId != null) { //老手机号
//                throw new CustomizedException("该手机号已经被注册过");
                UserInfo userInfo = userInfoMapper.getUserInfoByUserId(thirdUserBindPhoneInfo.getUserId());
                if (userInfo.getLoginSource().equals("WeChat")) {
                    userInfoMapper.updateUserWeChatByThirdInfo(userId, userInfo.getOpenId(), userInfo.getLoginSource());
                } else if (userInfo.getLoginSource().equals("QQ")){
                    userInfoMapper.updateUserQQByThirdInfo(userId, userInfo.getOpenId(), userInfo.getLoginSource());
                }
                //被逼的，为了适应iOS第一版本不得不做的（当三方登陆用户绑定的手机号是已有手机号的时候，合并用户，保留手机号那一条，删除三方用户那条）
                userInfoMapper.deleteUserInfo(thirdUserBindPhoneInfo.getUserId());
            } else {
                userInfoMapper.updateThirdUserByPhoneInfo(thirdUserBindPhoneInfo.getUserId(), thirdUserBindPhoneInfo.getPhone());
            }
//            userInfoMapper.updateThirdUserByPhoneInfo(thirdUserBindPhoneInfo.getUserId(), thirdUserBindPhoneInfo.getPhone());

            responseMap.put("user", userInfoMapper.getUserInfoByPhone(thirdUserBindPhoneInfo.getPhone()));

            resultDate.setCode(200);
            resultDate.setData(responseMap);

        } else if (verificationCode == null) {
            throw new CustomizedException("请先获取验证码");
        } else {
            throw new CustomizedException("验证码错误");
        }

        return resultDate;
    }

    @ApiOperation(value = "绑定微信、qq(changed)", notes = "根据id来指定添加用户手机号(id,openId,loginSource)")
    @ApiImplicitParam(name = "BindUserThirdInfo", value = "BindUserInfo实体", required = true, dataType = "BindUserThirdInfo")
    @RequestMapping(value = "/thirdinfo", method = RequestMethod.PUT)
    public ResultDate BindUserThirdInfo(@ModelAttribute BindUserThirdInfo bindUserThirdInfo) throws CustomizedException {

        if (bindUserThirdInfo.getUserId() == null)
            throw new CustomizedException("用户ID不能为空");

        if (bindUserThirdInfo.getOpenId() == null && bindUserThirdInfo.getQqOpenId() == null)
            throw new CustomizedException("openID不能为空");

        if (bindUserThirdInfo.getLoginSource() == null)
            throw new CustomizedException("来源不能为空");

        //根据用户手机号去查询用户是否已经是注册用户
        String openId = userInfoMapper.isThirdRegisteredUser(bindUserThirdInfo.getUserId());
        //判断openId是否存在于user_info表中
        if (openId != null)
            throw new CustomizedException("该用户已绑定某一三方账号");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        if (bindUserThirdInfo.getLoginSource().equals("WeChat")) {
            userInfoMapper.updateUserWeChatByThirdInfo(bindUserThirdInfo.getUserId(), bindUserThirdInfo.getOpenId(), bindUserThirdInfo.getLoginSource());
        } else if (bindUserThirdInfo.getLoginSource().equals("QQ")) {
            userInfoMapper.updateUserQQByThirdInfo(bindUserThirdInfo.getUserId(), bindUserThirdInfo.getQqOpenId(), bindUserThirdInfo.getLoginSource());
        }

        responseMap.put("user", userInfoMapper.getUserInfoByUserId(bindUserThirdInfo.getUserId()));

        resultDate.setCode(200);
        resultDate.setData(responseMap);


        return resultDate;
    }

    @ApiOperation(value = "绑定email", notes = "根据id来指定添加用户手机号(id,openId,loginSource)")
    @ApiImplicitParam(name = "BindUserEmailInfo", value = "BindUserEmailInfo实体", required = true, dataType = "BindUserEmailInfo")
    @RequestMapping(value = "/emailinfo", method = RequestMethod.PUT)
    public ResultDate BindUserEmailInfo(@ModelAttribute BindUserEmailInfo bindUserEmailInfo) throws CustomizedException {

        if (bindUserEmailInfo.getUserId() == null)
            throw new CustomizedException("用户ID不能为空");

        if (bindUserEmailInfo.getEmail() == null)
            throw new CustomizedException("email不能为空");

        //根据用户id去查询是否有该用户
        UserInfo userInfo = userInfoMapper.getUserInfoByUserId(bindUserEmailInfo.getUserId());
        if (userInfo == null)
            throw new CustomizedException("无该用户");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        userInfoMapper.updateUserByEmailInfo(bindUserEmailInfo.getUserId(), bindUserEmailInfo.getEmail());

        responseMap.put("user", userInfoMapper.getUserInfoByUserId(bindUserEmailInfo.getUserId()));

        resultDate.setCode(200);
        resultDate.setData(responseMap);

        return resultDate;
    }


    @ApiOperation(value = "设置密码", notes = "根据id来指定添加用户手机号(id,openId,loginSource)")
    @ApiImplicitParam(name = "BindUserPasswordInfo", value = "BindUserPasswordInfo实体", required = true, dataType = "BindUserPasswordInfo")
    @RequestMapping(value = "/passwordinfo", method = RequestMethod.PUT)
    public ResultDate BindUserPasswordInfo(@ModelAttribute BindUserPasswordInfo bindUserPasswordInfo) throws CustomizedException {

        if (bindUserPasswordInfo.getUserId() == null)
            throw new CustomizedException("用户ID不能为空");

        if (bindUserPasswordInfo.getPassword() == null)
            throw new CustomizedException("密码不能为空");

        if (bindUserPasswordInfo.getPhone() == null)
            throw new CustomizedException("手机号不能为空");

        if (bindUserPasswordInfo.getCode() == null)
            throw new CustomizedException("验证码不能为空");

        if (!PhoneUtil.isMobile(bindUserPasswordInfo.getPhone()))
            throw new CustomizedException("请填写正确的手机号");

        //根据用户id去查询是否有该用户
        UserInfo userInfo = userInfoMapper.getUserInfoByUserId(bindUserPasswordInfo.getUserId());
        if (userInfo == null)
            throw new CustomizedException("无该用户");

        //判断验证码是否合法（包括正确与否，失效与否两方面验证）
        VerificationCode verificationCode = verificationCodeMapper.getVerificationCodeByPhone(bindUserPasswordInfo.getPhone());

        if (verificationCode == null)
            throw new CustomizedException("请先获取验证码");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();


        //验证用户输入的验证码和数据库ver_code表中保寸的验证码是否一致
        if (bindUserPasswordInfo.getCode().equals(verificationCode.getCodeContent())) {

            userInfoMapper.updateUserByPasswordInfo(bindUserPasswordInfo.getUserId(), bindUserPasswordInfo.getPassword());

            responseMap.put("user", userInfoMapper.getUserInfoByUserId(bindUserPasswordInfo.getUserId()));

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
