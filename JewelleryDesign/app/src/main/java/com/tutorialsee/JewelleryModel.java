package com.tutorialsee;


public class JewelleryModel {

    // string course_name for storing course_name
    // and imgId for storing image id.
    private String course_name;
    private int imgId;
    private String desc;
    private String prodId;

    public JewelleryModel(String course_name, int imgId, String desc, String prodId) {
        this.course_name = course_name;
        this.imgId = imgId;
        this.desc = desc;
        this.prodId = prodId;

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


    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.desc = prodId;
    }

}