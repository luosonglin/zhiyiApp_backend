package cn.luosonglin.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by luosonglin on 24/11/2016.
 */
@Component
public class TestProperties {

    @Value("${cn.luosonglin.test.name}")
    private String name;
    @Value("${cn.luosonglin.test.title}")
    private String title;
    @Value("${cn.luosonglin.test.desc}")
    private String desc;

    @Value("${cn.luosonglin.test.value}")
    private String value;
    @Value("${cn.luosonglin.test.number}")
    private Integer number;
    @Value("${cn.luosonglin.test.bignumber}")
    private Long bignumber;
    @Value("${cn.luosonglin.test.test1}")
    private Integer test1;
    @Value("${cn.luosonglin.test.test2}")
    private Integer test2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getBignumber() {
        return bignumber;
    }

    public void setBignumber(Long bignumber) {
        this.bignumber = bignumber;
    }

    public Integer getTest1() {
        return test1;
    }

    public void setTest1(Integer test1) {
        this.test1 = test1;
    }

    public Integer getTest2() {
        return test2;
    }

    public void setTest2(Integer test2) {
        this.test2 = test2;
    }
    
}
