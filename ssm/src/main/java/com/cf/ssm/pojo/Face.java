package com.cf.ssm.pojo;

public class Face {

    private Integer id;
    private Integer age;
    private Double beauty;
    private String gender;
    private String glasses;
    private String emotion;
    private String imgPath;
    private String faceType;
    private String faceShape;
    private Integer faceNum;
    private Integer userId;
    private Integer isDelete;

    private Integer isShow;

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    private String faceId;

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public Face() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getBeauty() {
        return beauty;
    }

    public void setBeauty(Double beauty) {
        this.beauty = beauty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGlasses() {
        return glasses;
    }

    public void setGlasses(String glasses) {
        this.glasses = glasses;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getFaceType() {
        return faceType;
    }

    public void setFaceType(String faceType) {
        this.faceType = faceType;
    }

    public String getFaceShape() {
        return faceShape;
    }

    public void setFaceShape(String faceShape) {
        this.faceShape = faceShape;
    }

    public Integer getFaceNum() {
        return faceNum;
    }

    public void setFaceNum(Integer faceNum) {
        this.faceNum = faceNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Face{" +
                "id=" + id +
                ", age=" + age +
                ", beauty=" + beauty +
                ", gender='" + gender + '\'' +
                ", glasses='" + glasses + '\'' +
                ", emotion='" + emotion + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", faceType='" + faceType + '\'' +
                ", faceShape='" + faceShape + '\'' +
                ", faceNum=" + faceNum +
                ", userId=" + userId +
                ", isDelete=" + isDelete +
                '}';
    }
}
