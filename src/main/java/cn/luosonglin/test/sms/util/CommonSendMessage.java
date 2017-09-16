package cn.luosonglin.test.sms.util;

import cn.luosonglin.test.base.util.MD5Gen;
import cn.luosonglin.test.base.util.TimeUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by healife-605 on 2016/11/10.
 *
 * 修改短信内容
 * Modified by luosonglin on 2016/11/26.
 */

public class CommonSendMessage {


    /**
     * 发送短信的方法
     * @return
     */
    public static String toSend(String phone ,String code){

//        String url ="http://www.yzmsms.cn/sendSmsYZM.do";
//        String username="zhiyi";//"zhiyiyzm";  //账e号
//        String password="JkAOW03F"; //"FnSB0R";  //密码
//        String tkey= TimeUtil.getNowTime("yyyyMMddHHmmss");
//        String mobile=phone;  //发送的手机号
//        String content="【医会宝】您正在登录验证，验证码"+code+"，请在10分钟内按APP页面提示提交验证码，切勿将验证码泄漏于他人。";//助通科技 内容仿阿里
////        String content="您的验证码是:"+code+" 10分钟内有效【助通科技】";
//
//        //String time="2016-09-06 17:48:22";//定时信息所需参数时间格式为yyyy-MM-dd HH:mm:ss
//        String xh="";
//        try {
//            content= URLEncoder.encode(content, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String param="username="+username+"&password="+ MD5Gen.getMD5(MD5Gen.getMD5(password)+tkey)+"&tkey="+tkey+"&mobile="+mobile+"&content="+content+"&xh="+xh;
//        //String param="url="+url+"&username="+username+"&password="+MD5Gen.getMD5(MD5Gen.getMD5(password)+tkey)+"&tkey="+tkey+"&time="+time+"&mobile="+mobile+"&content="+content+"&xh="+xh;//定时信息url输出
//        String ret=HttpRequest.sendPost(url,param);//定时信息只可为post方式提交
//        System.out.println("ret:"+ret);
//        System.out.println(param);
//        return ret;

        //新版助通发送短信接口
        String url ="http://www.ztsms.cn/sendNSms.do";
        String username="zhiyi";  //账e号
        String password="JkAOW03F";  //密码
        String tkey= TimeUtil.getNowTime("yyyyMMddHHmmss");
        String mobile=phone;  //发送的手机号
        String content="您的验证码是:"+code+" ,10分钟内有效【医会宝】";
        String productid = "676767";//优质短信验证码专用

        String xh="";
        try {
            content= URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String param="username="+username+"&password="+MD5Gen.getMD5(MD5Gen.getMD5(password)+tkey)+"&tkey="+tkey+"&mobile="+mobile+"&content="+content+"&productid="+productid+"&xh="+xh;
        System.out.println("*********param*********"+param);
        String ret=HttpRequest.sendPost(url,param);
        System.out.println("****************ret"+ret);
        return ret;
    }

    //转意第三方返回值
    public static String changeReturnName(String result){

        if("1".equals(result)){
            return "success";
        }else{
            return "error";
        }

    }

}
