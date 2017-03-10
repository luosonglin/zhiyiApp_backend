package cn.luosonglin.test.member.entity;

/**
 * Created by luosonglin on 10/03/2017.
 */
public class BindUserPasswordInfo {
    private Integer userId;
    private String password;
    private String phone;
    private String code;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
