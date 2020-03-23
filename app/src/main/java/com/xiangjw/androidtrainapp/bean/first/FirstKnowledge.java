package com.xiangjw.androidtrainapp.bean.first;

public class FirstKnowledge {
    private String name;

    private String type;

    private String subject;

    private String createTime;

    private String updateTime;

    public FirstKnowledge(String name,String type , String subject, String createTime) {
        this.name = name;
        this.type = type;
        this.subject = subject;
        this.createTime = createTime;
        this.updateTime = createTime;
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
}
