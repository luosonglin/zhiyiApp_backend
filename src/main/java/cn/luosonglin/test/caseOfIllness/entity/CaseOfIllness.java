package cn.luosonglin.test.caseOfIllness.entity;

import java.util.Date;

/**
 * Created by luosonglin on 07/01/2017.
 */
public class CaseOfIllness {
    private Integer id;
    private Integer userId;
    private String title;
    private String tagId;
    private String chiefComplain;
    private String chiefComplainImage;
    private String bodyCheck;
    private String bodyCheckImage;
    private String medicalDiagnosis;//medical_diagnosis
    private String medicalDiagnosisImage;
    private String followUp;
    private String followUpImage;
    private Integer commentCount;
    private Integer likeCount;
    private Date createdAt;
    private Date deletedAt;
    private Integer isHot;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getChiefComplain() {
        return chiefComplain;
    }

    public void setChiefComplain(String chiefComplain) {
        this.chiefComplain = chiefComplain;
    }

    public String getChiefComplainImage() {
        return chiefComplainImage;
    }

    public void setChiefComplainImage(String chiefComplainImage) {
        this.chiefComplainImage = chiefComplainImage;
    }

    public String getBodyCheck() {
        return bodyCheck;
    }

    public void setBodyCheck(String bodyCheck) {
        this.bodyCheck = bodyCheck;
    }

    public String getBodyCheckImage() {
        return bodyCheckImage;
    }

    public void setBodyCheckImage(String bodyCheckImage) {
        this.bodyCheckImage = bodyCheckImage;
    }

    public String getMedicalDiagnosis() {
        return medicalDiagnosis;
    }

    public void setMedicalDiagnosis(String medicalDiagnosis) {
        this.medicalDiagnosis = medicalDiagnosis;
    }

    public String getMedicalDiagnosisImage() {
        return medicalDiagnosisImage;
    }

    public void setMedicalDiagnosisImage(String medicalDiagnosisImage) {
        this.medicalDiagnosisImage = medicalDiagnosisImage;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getFollowUpImage() {
        return followUpImage;
    }

    public void setFollowUpImage(String followUpImage) {
        this.followUpImage = followUpImage;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }
}
