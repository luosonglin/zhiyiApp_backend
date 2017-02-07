package cn.luosonglin.test.relationship.entity;

import java.util.Date;

/**
 * Created by luosonglin on 29/11/2016.
 *
 * 关系表
 */
public class UsersRelationship {
    private Integer tid;
    private Integer fromuid;
    private Integer touid;
    private Date addTime;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getFromuid() {
        return fromuid;
    }

    public void setFromuid(Integer fromuid) {
        this.fromuid = fromuid;
    }

    public Integer getTouid() {
        return touid;
    }

    public void setTouid(Integer touid) {
        this.touid = touid;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}







