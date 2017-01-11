package cn.luosonglin.test.member.entity;

/**
 * Created by luosonglin on 10/01/2017.
 */
public class ThirdUser {
    private String openId;
    private String nickName;
    private String platform;
    private String iconurl;//头像

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
}
