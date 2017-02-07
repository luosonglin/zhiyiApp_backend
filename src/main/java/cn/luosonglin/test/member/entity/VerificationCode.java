package cn.luosonglin.test.member.entity;

import java.util.Date;

/**
 * Created by luosonglin on 25/11/2016.
 *
 * 验证码
 */
public class VerificationCode {
    private Integer id;
    private java.util.Date send_date;
    private String phone;
    private String code_content;
    private java.util.Date sub_date;
    private String source;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSendDate() {
        return send_date;
    }

    public void setSendDate(Date send_date) {
        this.send_date = send_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCodeContent() {
        return code_content;
    }

    public void setCodeContent(String code_content) {
        this.code_content = code_content;
    }

    public Date getSubDate() {
        return sub_date;
    }

    public void setSubDate(Date sub_date) {
        this.sub_date = sub_date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
