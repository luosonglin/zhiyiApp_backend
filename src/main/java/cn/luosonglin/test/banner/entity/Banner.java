package cn.luosonglin.test.banner.entity;

import java.util.Date;

/**
 * Created by luosonglin on 01/12/2016.
 */
public class Banner {
    private Integer id;
    private String url;
    private String title;
    private String content;
    private Date created_at;
    private Integer is_display;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Date getCreate_at() {
        return created_at;
    }

    public void setCreate_at(Date created_at) {
        this.created_at = created_at;
    }

    public Integer getIs_display() {
        return is_display;
    }

    public void setIs_display(Integer is_display) {
        this.is_display = is_display;
    }
}
