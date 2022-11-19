package com.cf.ssm.controller;

import com.cf.ssm.pojo.Face;
import com.cf.ssm.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FaceController {

    @Autowired
    private FaceService faceService;

    @RequestMapping("/getTopFace")
    public List<Face> getTopFace(int num){
        return faceService.getTopFace(num);
    }

    @PostMapping ("/getAllFace")
    public List<Face> getAllFace(){
        return faceService.getAllFace();
    }

    @RequestMapping("/getFaceByFaceId")
    public Face getFaceByFaceId(String faceId){
        return faceService.getFaceByFaceId(faceId);
    }

    @RequestMapping("/getFaceByUser")
    public List<Face> getFaceByUser(String userId){
        return faceService.getFaceByUser(userId);
    }

    @PutMapping("/updateIsShow")
    public Map<String,Object> updateIsShow(int isShow,int id){
        return faceService.updateIsShow(isShow,id);
    }

    @PutMapping("/updateISDelete")
    public Map<String,Object> updateISDelete(int isDelete,int id){
        return faceService.updateISDelete(isDelete,id);
    }

    @RequestMapping("/randomPK")
    public Map<String,Object> randomPK(String faceId,Integer userId){
        return faceService.randomPK(faceId,userId);
    }
}
