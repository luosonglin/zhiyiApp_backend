package cn.luosonglin.test.sms.service;

/**
 * 通用发送短信的service
 * Created by healife-605 on 2016/11/10.
 *
 * Modified by luosonglin on 2016/11/26.
 */
public interface SendCommonMessageService {

    //发送短信接口
    public abstract String sendCommonMessage(String phone, String message, String sendType);

}
