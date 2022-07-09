package com.tutorialsee;


public class JewelleryModel {

    // string course_name for storing course_name
    // and imgId for storing image id.
    private String course_name;
    private int imgId;
    private String desc;

    public JewelleryModel(String course_name, int imgId, String desc) {
        this.course_name = course_name;
        this.imgId = imgId;
        this.desc = desc;

    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getimgId() {
        return imgId;
    }

    public void setimgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}