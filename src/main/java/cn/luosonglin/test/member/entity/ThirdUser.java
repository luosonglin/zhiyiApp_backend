package cn.luosonglin.test.member.entity;

import cn.luosonglin.test.base.util.CodeUtil;

/**
 * Created by luosonglin on 10/01/2017.
 */
public class ThirdUser {
    private String openId;
    private String nickName;
    private String platform;//
    private String iconurl;//头像
    private String mobilePhone;
    private String code;
    private String qqOpenId; //qq open id
    private String userSource;//ios an pc

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return String.valueOf(CodeUtil.decode(nickName));
    }

    public void setNickName(String nickName) {
        this.nickName = CodeUtil.encode(nickName.getBytes());
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }
}
