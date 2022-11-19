package com.cf.ssm.mapper;

import com.cf.ssm.pojo.Face;
import com.cf.ssm.pojo.FaceRelevance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FaceRelevanceMapper {
    int insertFaceRelevance(@Param("faceId1") String faceId1, @Param("faceId2") String faceId2, @Param("userId") int userId, @Param("beauty") Double beauty,@Param("winnerUrl") String winnerUrl);

    FaceRelevance getFaceRelevance(String faceId1);

    List<Face> getFacePKLeft(int userId);

    List<Face> getFacePKRight(int userId);
}
