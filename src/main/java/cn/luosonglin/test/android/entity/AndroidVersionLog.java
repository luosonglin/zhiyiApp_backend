package cn.luosonglin.test.android.entity;

import java.util.Date;

/**
 * Created by luosonglin on 05/09/2017.
 */
public class AndroidVersionLog {

    private Integer userId;
    private String oldVersionId;
    private String newVersionId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOldVersionId() {
        return oldVersionId;
    }

    public void setOldVersionId(String oldVersionId) {
        this.oldVersionId = oldVersionId;
    }

    public String getNewVersionId() {
        return newVersionId;
    }

    public void setNewVersionId(String newVersionId) {
        this.newVersionId = newVersionId;
    }
}
