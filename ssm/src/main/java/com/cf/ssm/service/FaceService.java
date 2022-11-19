package com.cf.ssm.service;

import com.cf.ssm.pojo.Face;

import java.util.List;
import java.util.Map;

public interface FaceService {

    int insertFace(Face face);

    List<Face> getTopFace(int num);

    List<Face> getAllFace();

    Face getFaceByFaceId(String faceId);

    List<Face> getFaceByUser(String userId);

    Map<String,Object> updateIsShow(int isShow,int id);

    Map<String, Object> updateISDelete(int isDelete, int id);

    Map<String, Object> randomPK(String faceId, Integer userId);
}
