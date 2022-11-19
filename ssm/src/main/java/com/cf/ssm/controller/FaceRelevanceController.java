package com.cf.ssm.controller;

import com.cf.ssm.pojo.Face;
import com.cf.ssm.pojo.FaceRelevance;
import com.cf.ssm.service.FaceRelevanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FaceRelevanceController {

    @Autowired
    private FaceRelevanceService faceRelevanceService;

    @RequestMapping("/insertFaceRelevance")
    public int insertFaceRelevance(String faceId1,String faceId2,int userId){
        return faceRelevanceService.insertFaceRelevance(faceId1,faceId2,userId);
    }

    @RequestMapping("/getFaceRelevance")
    public FaceRelevance getFaceRelevance(String faceId1){
        return faceRelevanceService.getFaceRelevance(faceId1);
    }

    @RequestMapping("/getFacePKLeft")
    public List<Face> getFacePK(int userId){
        return faceRelevanceService.getFacePKLeft(userId);
    }

    @RequestMapping("/getFacePKRight")
    public List<Face> getFacePKRight(int userId){
        return faceRelevanceService.getFacePKRight(userId);
    }
}
