package com.cf.ssm.service.impl;

import com.cf.ssm.mapper.FaceRelevanceMapper;
import com.cf.ssm.pojo.Face;
import com.cf.ssm.pojo.FaceRelevance;
import com.cf.ssm.service.FaceRelevanceService;
import com.cf.ssm.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaceRelevanceServiceImpl implements FaceRelevanceService {
    @Autowired
    private FaceRelevanceMapper faceRelevanceMapper;

    @Autowired
    private FaceService faceService;
    @Override
    public int insertFaceRelevance(String faceId1, String faceId2, int userId) {
        Face faceByFaceId1 = faceService.getFaceByFaceId(faceId1);
        Face faceByFaceId2 = faceService.getFaceByFaceId(faceId2);
        // 获得face的打分
        Double beauty1 = faceByFaceId1.getBeauty();
        Double beauty2 = faceByFaceId2.getBeauty();
        String imgPath1 = faceByFaceId1.getImgPath();
        String imgPath2 = faceByFaceId2.getImgPath();
        Double beauty = null;
        String winnerUrl = null;
        if (beauty1 != beauty2) {
            if(beauty1 > beauty2){
                beauty = beauty1;
                winnerUrl = imgPath1;
            }else {
                beauty = beauty2;
                winnerUrl = imgPath2;
            }
        }else{
            // 分数相同参数
            beauty = 999.0;
        }
        return faceRelevanceMapper.insertFaceRelevance(faceId1,faceId2,userId,beauty,winnerUrl);
    }

    @Override
    public FaceRelevance getFaceRelevance(String faceId1) {
        return faceRelevanceMapper.getFaceRelevance(faceId1);
    }

    @Override
    public List<Face> getFacePKLeft(int userId) {
        return faceRelevanceMapper.getFacePKLeft(userId);
    }

    @Override
    public List<Face> getFacePKRight(int userId) {
        return faceRelevanceMapper.getFacePKRight(userId);
    }
}
