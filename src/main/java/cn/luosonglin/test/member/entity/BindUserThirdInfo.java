package cn.luosonglin.test.member.entity;

/**
 * Created by luosonglin on 10/03/2017.
 */
public class BindUserThirdInfo {
    private Integer userId;
    private String openId;
    private String qqOpenId;
    private String loginSource;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }
}
