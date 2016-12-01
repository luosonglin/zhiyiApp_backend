package cn.luosonglin.test.blog.entity;

import java.util.Date;

/**
 * Created by luosonglin on 28/11/2016.
 *
 * !!!注意：此处需要与数据库表的字段一致！不可擅自使用其他驼峰式写法!!!
 */
public class Blog {
//    private Integer id;
//    private Integer user_id;
//    private String title;
//    private String content;
//    private Integer comment_count;
//    private Integer like_count;
//    private Date created_at;
//    private Date deleted_at;
//    private String tag_id;
//    private Integer is_hot;
//    private String images;

    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Integer commentCount;
    private Integer likeCount;
    private Date createdAt;
    private Date deletedAt;
    private String tagId;
    private Integer isHot;
    private String images;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
