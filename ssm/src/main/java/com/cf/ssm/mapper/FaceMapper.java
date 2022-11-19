package com.cf.ssm.mapper;

import com.cf.ssm.pojo.Face;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaceMapper {

    int insertFace(Face face);

    List<Face> getTopFace(int num);

    List<Face> getAllFace();

    Face getFaceByFaceId(String faceId);

    List<Face> getFaceByUser(String userId);

    int updateIsShow(@Param("isShow") int isShow,@Param("id") int id);

    int updateISDelete(@Param("isDelete")int isDelete,@Param("id") int id);

    List<Integer> getFaceId();

    Face getFaceById(Integer id);
}
