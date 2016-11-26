package cn.luosonglin.test.sms.entity;

import java.util.Date;

/**
 * Created by luosonglin on 26/11/2016.
 */
public class ShorMessageRecords {
    private Integer messageRecordId;
    private Integer userId;
    private Integer eventId;
    private String phone;
    private String returnValue;
    private String messageContent;
    private java.util.Date sendTime;
    private String sendType;

    public Integer getMessageRecordId() {
        return messageRecordId;
    }

    public void setMessageRecordId(Integer messageRecordId) {
        this.messageRecordId = messageRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
}
