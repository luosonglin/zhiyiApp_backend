package cn.luosonglin.test.android.entity;

import java.util.Date;

/**
 * Created by luosonglin on 16/09/2017.
 */
public class AndroidPhoneInfo {
    private Integer userId;
    private String uniqueSerialNumber;
    private String metrics;
    private String imei;
    private String imsi;
    private String macAddress;
    private String mccMnc;
    private String simOperatorName;
    private int isRoot;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUniqueSerialNumber() {
        return uniqueSerialNumber;
    }

    public void setUniqueSerialNumber(String uniqueSerialNumber) {
        this.uniqueSerialNumber = uniqueSerialNumber;
    }

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMccMnc() {
        return mccMnc;
    }

    public void setMccMnc(String mccMnc) {
        this.mccMnc = mccMnc;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public int getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(int isRoot) {
        this.isRoot = isRoot;
    }
}
