package com.cf.ssm.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.cf.ssm.mapper.FaceMapper;
import com.cf.ssm.mapper.FaceRelevanceMapper;
import com.cf.ssm.pojo.Face;
import com.cf.ssm.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FaceServiceImpl implements FaceService {

    @Autowired
    private FaceMapper faceMapper;

    @Autowired
    private FaceRelevanceMapper faceRelevanceMapper;

    public int insertFace(Face face){
        return faceMapper.insertFace(face);
    }

    @Override
    public List<Face> getTopFace(int num) {
        return faceMapper.getTopFace(num);
    }

    @Override
    public List<Face> getAllFace() {
        return faceMapper.getAllFace();
    }

    @Override
    public Face getFaceByFaceId(String faceId) {
        return faceMapper.getFaceByFaceId(faceId);
    }

    @Override
    public List<Face> getFaceByUser(String userId) {
        return faceMapper.getFaceByUser(userId);
    }

    @Override
    public Map<String,Object> updateIsShow(int isShow,int id) {
        Map<String,Object> resultMap = new HashMap<>();
        int result = faceMapper.updateIsShow(isShow,id);
        if (result > 0){
            resultMap.put("code","200");
            resultMap.put("message","修改成功");
        }else {
            resultMap.put("code","400");
            resultMap.put("message","修改失败");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> updateISDelete(int isDelete, int id) {
        Map<String,Object> resultMap = new HashMap<>();
        int result = faceMapper.updateISDelete(isDelete,id);
        if (result > 0){
            resultMap.put("code","200");
            resultMap.put("message","删除成功");
        }else {
            resultMap.put("code","400");
            resultMap.put("message","删除失败");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> randomPK(String faceId, Integer userId) {
        Map<String,Object> resultMap = new HashMap<>();
        // 根据faceId查询上传的照片的信息---beauty
        Face face1 = faceMapper.getFaceByFaceId(faceId);
        // 获得id的值的列表
        List<Integer> faceId1 = faceMapper.getFaceId();
        System.out.println(faceId1);
        // 从中随机抽取一个id获得需要的信息
        Integer id = RandomUtil.randomEle(faceId1);
        Face face2 = faceMapper.getFaceById(id);
        // 与上传的照片进行比较
        if (face1.getBeauty() > face2.getBeauty()){
            resultMap.put("code","200");
            resultMap.put("beauty",face1.getBeauty());
            resultMap.put("imgPath",face1.getImgPath());
            // 通过userId距离PK记录
            faceRelevanceMapper.insertFaceRelevance(face1.getFaceId(),face2.getFaceId(),userId,face1.getBeauty(),face1.getImgPath());
        }
        else if (face1.getBeauty() < face2.getBeauty()){
            resultMap.put("code","200");
            resultMap.put("beauty",face2.getBeauty());
            resultMap.put("imgPath",face2.getImgPath());
            // 通过userId距离PK记录
            faceRelevanceMapper.insertFaceRelevance(face1.getFaceId(),face2.getFaceId(),userId,face2.getBeauty(),face2.getImgPath());
        }
        else {
            resultMap.put("code","300");
            resultMap.put("message","你的照片和随机抽取的照片一样美");
        }
        return resultMap;
    }
}
