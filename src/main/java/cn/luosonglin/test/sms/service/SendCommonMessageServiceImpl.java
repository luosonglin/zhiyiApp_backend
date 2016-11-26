package cn.luosonglin.test.sms.service;

import cn.luosonglin.test.sms.dao.shortMessageRecordsMapper;
import cn.luosonglin.test.sms.entity.ShorMessageRecords;
import cn.luosonglin.test.sms.util.CommonSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by healife-605 on 2016/11/10.
 *
 * Modified by luosonglin on 2016/11/26.
 */
@Service("CommonSendMessageService")
public class SendCommonMessageServiceImpl implements SendCommonMessageService {

    @Autowired
    private shortMessageRecordsMapper shortMessageRecordsMapper;

    /**
     * 定义发送短信的业务方法
     * @param phone
     * @param message
     * @return
     */
    @Override
    public String sendCommonMessage(String phone, String message ,String sendType) {

        //调用供应商的发送接口并接受真是发送短信的第三方返回值
        String result = CommonSendMessage.toSend(phone,message);

        //将发送结果写入短信发送记录表中
        ShorMessageRecords shorMessageRecords = new ShorMessageRecords();

        shorMessageRecords.setPhone(phone);
        shorMessageRecords.setReturnValue(result.substring(0,result.indexOf(",")));
        shorMessageRecords.setSendTime(new Date());
        shorMessageRecords.setMessageContent(message);

//        if("yzm".equals(sendType)){
//            shorMessageRecords.setSendType("yzm");
//        }else if("tz".equals(sendType)){
//            shorMessageRecords.setSendType("tz");
//        }
        shorMessageRecords.setSendType(sendType);   //2种类型："yzm"，"tz"

        //将短信发送记录保存到数据库中
        shortMessageRecordsMapper.insertShortMessageRecords(shorMessageRecords);
//        shorMessageRecordsService.saveShorMessageRecords(shorMessageRecords ,"add");

        return result.substring(0,result.indexOf(","));
    }
}
