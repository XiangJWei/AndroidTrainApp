package com.xiangjw.androidtrainapp.bean.second;

import com.xiangjw.androidtrainapp.R;
import com.xiangjw.androidtrainapp.utils.StringUtils;

public class SecondKnowledge {
    private String name;

    private String type;

    private String subject;

    private String createTime;

    private String updateTime;

    private int img;

    public SecondKnowledge(String name, String type, String subject, String createTime, int img) {
        this.name = name;
        this.type = type;
        this.subject = subject;
        this.createTime = createTime;
        this.img = img;
    }

    public SecondKnowledge(String name, String type, String subject, String createTime) {
        this.name = name;
        this.type = type;
        this.subject = subject;
        this.createTime = createTime;
        this.img = 0;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getImg() {
        return img;
    }

    public boolean showSubJect(){
        return StringUtils.isNotEmpty(subject);
    }
}
