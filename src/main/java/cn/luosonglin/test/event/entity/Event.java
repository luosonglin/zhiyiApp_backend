package cn.luosonglin.test.event.entity;

import java.util.Date;

/**
 * Created by luosonglin on 23/03/2017.
 */
public class Event {
    private Integer id;
    private String title;
    private String banner; //banner图
    private Date startDate;
    private Date endDate;

    private String address;//会议地址
    private Integer creditNum; //学分数
    private Integer dhotOrder;//热门会议的显示序号（从1开始，自上而下显示

    private String hot;//是否标为热门会议(true,false)
    private String news;//是否标为最新会议(true,false)
    private String groom;//是否标为推荐会议(true,false)

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
        this.banner = banner;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCreditNum() {
        return creditNum;
    }

    public void setCreditNum(Integer creditNum) {
        this.creditNum = creditNum;
    }

    public Integer getDhotOrder() {
        return dhotOrder;
    }

    public void setDhotOrder(Integer dhotOrder) {
        this.dhotOrder = dhotOrder;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getGroom() {
        return groom;
    }

    public void setGroom(String groom) {
        this.groom = groom;
    }
}
