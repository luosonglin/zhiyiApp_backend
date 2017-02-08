package cn.luosonglin.test.caseOfIllness.entity;

import java.util.Date;

/**
 * Created by luosonglin on 08/02/2017.
 */
public class UserAndCaseLike {

    private Integer id;
    private Integer userId;
    private Integer caseId;
    private Date createdAt;
    private Integer isDisplay;

    private String name;
    private String nickName;
    private String company;
    private String userPic;
    private String authenStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getAuthenStatus() {
        return authenStatus;
    }

    public void setAuthenStatus(String authenStatus) {
        this.authenStatus = authenStatus;
    }
}
