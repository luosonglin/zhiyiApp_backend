package cn.luosonglin.test.sms.entity;

import java.util.Date;

/**
 * Created by luosonglin on 26/11/2016.
 */
public class ShorMessageRecords {
    private Integer message_recordId;
    private Integer user_id;
    private Integer event_id;
    private String phone;
    private String return_value;
    private String message_content;
    private java.util.Date send_time;
    private String send_type;

    public Integer getMessage_recordId() {
        return message_recordId;
    }

    public void setMessage_recordId(Integer message_recordId) {
        this.message_recordId = message_recordId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReturn_value() {
        return return_value;
    }

    public void setReturn_value(String return_value) {
        this.return_value = return_value;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }
}
