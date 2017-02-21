package cn.luosonglin.test.banner.entity;

import java.util.Date;

/**
 * Created by luosonglin on 22/01/2017.
 * 会议
 */
public class EventBanner {
    private Integer id;
    private String title;
    private String banner; //banner图
    private Date startDate;
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = "http://www.medmeeting.com/img/modelImgs/"+banner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
