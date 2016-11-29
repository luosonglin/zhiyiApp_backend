package cn.luosonglin.test.blog.entity;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by luosonglin on 28/11/2016.
 *
 * !!!注意：此处需要与数据库表的字段一致！不可擅自使用其他驼峰式写法!!!
 */
public class Blog {
    private Integer id;
    private Integer user_id;
    private String title;
    private String content;
    private Integer comment_count;
    private Integer like_count;
    private Date created_at;
    private Date deleted_at;
    private String tag_id;
    private Integer is_hot;
    private String images;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
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
//        this.content = content.getBytes("gbk");
    }

    public Integer getCommentCount() {
        return comment_count;
    }

    public void setCommentCount(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getLikeCount() {
        return like_count;
    }

    public void setLikeCount(Integer like_count) {
        this.like_count = like_count;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDeletedAt() {
        return deleted_at;
    }

    public void setDeletedAt(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getTabId() {
        return tag_id;
    }

    public void setTabId(String tag_id) {
        this.tag_id = tag_id;
    }

    public Integer getIsHot() {
        return is_hot;
    }

    public void setIsHot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
