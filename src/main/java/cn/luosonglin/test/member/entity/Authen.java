package cn.luosonglin.test.member.entity;

import java.util.Date;

/**
 * Created by luosonglin on 21/02/2017.
 */
public class Authen {
    private Integer id;
    private Integer userId;//用户id
    private String userType;//用户类型(A:个人，B团队)
    private String authenStatus;//认证状态（A:已认证，B:待认证X:未认证）
    private java.util.Date stateDate;//申请认证时间
    private String orgName;
    private String orgType;
    private String contactName;
    private String mobliePhone;//手机号
    private String workPhone;
    private String orgEmail;
    private String country;
    private String province;
    private String city;
    private String zipCode;
    private String filePath;
    private String photoPath;
    private String authenDesc;
    private String orgDepar;
    private String orgAddress;
    private String orgLogo;
    private String userName;//个人认证用户名称
    private String userHospital;//个人认证用户所在医院
    private String orgNet;
    private String userDepartment;//个人认证用户所在科室
    private String userTitle;//个人注册用户的职称

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAuthenStatus() {
        return authenStatus;
    }

    public void setAuthenStatus(String authenStatus) {
        this.authenStatus = authenStatus;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMobliePhone() {
        return mobliePhone;
    }

    public void setMobliePhone(String mobliePhone) {
        this.mobliePhone = mobliePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getAuthenDesc() {
        return authenDesc;
    }

    public void setAuthenDesc(String authenDesc) {
        this.authenDesc = authenDesc;
    }

    public String getOrgDepar() {
        return orgDepar;
    }

    public void setOrgDepar(String orgDepar) {
        this.orgDepar = orgDepar;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHospital() {
        return userHospital;
    }

    public void setUserHospital(String userHospital) {
        this.userHospital = userHospital;
    }

    public String getOrgNet() {
        return orgNet;
    }

    public void setOrgNet(String orgNet) {
        this.orgNet = orgNet;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }
}
