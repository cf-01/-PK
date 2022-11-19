package com.cf.ssm.pojo;

public class FaceRelevance {

    private Integer id;

    private String faceId1;
    private String faceId2;
    private Integer userId;
    private Double beauty;
    private String winnerUrl;

    public String getWinnerUrl() {
        return winnerUrl;
    }

    public void setWinnerUrl(String winnerUrl) {
        this.winnerUrl = winnerUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaceId1() {
        return faceId1;
    }

    public void setFaceId1(String faceId1) {
        this.faceId1 = faceId1;
    }

    public String getFaceId2() {
        return faceId2;
    }

    public void setFaceId2(String faceId2) {
        this.faceId2 = faceId2;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getBeauty() {
        return beauty;
    }

    public void setBeauty(Double beauty) {
        this.beauty = beauty;
    }

    @Override
    public String toString() {
        return "FaceRelevance{" +
                "id=" + id +
                ", faceId1='" + faceId1 + '\'' +
                ", faceId2='" + faceId2 + '\'' +
                ", userId=" + userId +
                ", beauty=" + beauty +
                ", winnerUrl='" + winnerUrl + '\'' +
                '}';
    }
}
