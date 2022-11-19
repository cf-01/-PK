package com.cf.ssm.service;

import com.cf.ssm.pojo.Face;
import com.cf.ssm.pojo.FaceRelevance;

import java.util.List;

public interface FaceRelevanceService {
    int insertFaceRelevance(String faceId1, String faceId2, int userId);

    FaceRelevance getFaceRelevance(String faceId1);

    List<Face> getFacePKLeft(int userId);

    List<Face> getFacePKRight(int userId);
}
