package com.cf.ssm.controller;


import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.cf.ssm.pojo.Face;
import com.cf.ssm.pojo.FaceV3DetectBean;
import com.cf.ssm.service.FaceService;
import com.cf.ssm.utils.Base64Util;
import com.cf.ssm.utils.FaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FaceService faceService;

    @CrossOrigin
    @PostMapping("/upload")
    public Map<String,Object> uploadFile(@RequestParam(value = "file") MultipartFile file,Integer userId,
                                         HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        //将上传的照片转为二进制的数据流
        byte[] byface = Base64Util.FileToByte(file);
        //获取protocol通信协议
        String scheme = request.getScheme();// http
        //获取服务名称
        String serverName = request.getServerName();//localhost
        //获取端口号
        int serverPort = request.getServerPort();//8010
        //contextPath
        String contextPath = request.getContextPath();// /stasys_v3
        String url = scheme + "://" + serverName + ":" + serverPort + contextPath + "/photo/";

        //调用人脸检测的方法
        String encode = Base64Util.encode(byface);
        //将base64数据传入人脸检测工具
        String faceScore = FaceUtil.getFaceScore(encode);
        FaceV3DetectBean faceV3DetectBean = JSONUtil.toBean(faceScore, FaceV3DetectBean.class);
        if (faceV3DetectBean.getError_msg().equals("pic not has face")){
            map.put("url", null);
            map.put("msg","未检测到人脸请重新上传");
            map.put("code","400");
            return map;
        }
        if (faceV3DetectBean.getResult().getFace_num()>1){
            map.put("url", null);
            map.put("msg","人脸数量大于1");
            map.put("code","400");
            return map;
        }
        // 控制文件大小
        if (file.getSize() > 1024 * 1024 * 10) {
            map.put("msg", "文件大小不能超过10M");
            map.put("url", null);
            map.put("code","400");
            return map;
        }
        String fileUrl = null;
        try {
            // 保存文件到当前项目根目录下
            ServletContext servletContext = request.getServletContext();
            // 获取当前工程下photo目录的真实路径
            String realPath = servletContext.getRealPath("photo");
//            String localDirString = "D:\\Desktop\\face-score-pk\\ssm\\src\\main\\webapp\\photo";
            String localDirString = "D:\\文件存放\\作业\\SSM学习\\颜值PK\\face-score-pk\\ssm\\src\\main\\webapp\\photo";

            File dir = new File(realPath);
            File localDirPath = new File(localDirString);
            // 判断目录是否存在
            if (!dir.exists()) {
                dir.mkdirs();//不存在-创建新目录
            }
            if (!localDirPath.exists()) {
                localDirPath.mkdirs();//不存在-创建新目录
            }
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            // 使用UUID替换文件名——避免文件名冲突
            String uuid = UUID.randomUUID().toString();
            // 获取文件拓展名
            String extendsName = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 控制文件类型
            if (!extendsName.equals(".png")&&!extendsName.equals(".jpg")&&!extendsName.equals(".jpeg")) {
                map.put("status", false);
                map.put("msg", "文件类型错误,必须为.png,.jpg.,.jpeg压缩文件");
                map.put("url", null);
                map.put("code","400");
                return map;
            }
            // 新的文件名-056af4c8-2a5a-4e6e-a108-45f689865264.zip
            String newFileName = uuid.concat(extendsName);
            // 文件对应URL路径：http://localhost:8010/stasys_v3/upload056af4c8-2a5a-4e6e-a108-45f689865264.zip
            fileUrl = url.concat(newFileName);
            // 文件在服务器保存位置
            File saveLoc = new File(dir, newFileName);
            File localDir = new File(localDirPath, newFileName);
            // 保存文件
            file.transferTo(saveLoc);

            Files.copy(saveLoc.toPath(),localDir.toPath());
            // 填充返回值
            map.put("msg", "文件上传成功!");
            map.put("code","200");
            map.put("url", fileUrl);
            // 添加一个PK的face_Id
            String faceId = IdUtil.simpleUUID();
            if (userId == null){
                map.put("beauty",faceV3DetectBean.getResult().getFace_list().get(0).getBeauty());
            }else {
                map.put("faceId",faceId);
            }
            // 上传成功之后把数据插入数据库，调用FaceService
            //将需要的人脸检测数据封存入Face对象中
            Face face = new Face();

            if (faceV3DetectBean!=null) {
                for (int i = 0; i < faceV3DetectBean.getResult().getFace_list().size(); i++) {
                    // 获取年龄
                    int ageOne = faceV3DetectBean.getResult().getFace_list().get(i).getAge();
                    face.setAge(ageOne);

                    // 获取美丑打分
                    Double beautyOne = (Double) faceV3DetectBean.getResult().getFace_list().get(i).getBeauty();
                    face.setBeauty(beautyOne);

                    // 获取性别 male(男)、female(女)
                    String gender = faceV3DetectBean.getResult().getFace_list().get(i).getGender().getType();
                    face.setGender(gender);

                    // 获取是否带眼睛 none:无眼镜，common:普通眼镜，sun:墨镜
                    String glasses = faceV3DetectBean.getResult().getFace_list().get(i).getGlasses().getType();
                    face.setGlasses(glasses);

                    // 获取表情
                    String emotion = faceV3DetectBean.getResult().getFace_list().get(i).getEmotion().getType();
                    face.setEmotion(emotion);

                    // 获取脸型
                    String faceShape = faceV3DetectBean.getResult().getFace_list().get(i).getFace_shape().getType();
                    face.setFaceShape(faceShape);

                    // 获取是否真实人物
                    String faceType = faceV3DetectBean.getResult().getFace_list().get(i).getFace_type().getType();
                    face.setFaceType(faceType);

                    // 获取人脸数量
                    int face_num = faceV3DetectBean.getResult().getFace_num();
                    face.setFaceNum(face_num);

                    //获取用户id
                    face.setUserId(userId);

                    // 设置默认不开启
                    face.setIsShow(0);

                    //设置是否参与排行
                    face.setIsDelete(1);

                    //保存用户头像的对外访问地址在数据库中
                    face.setImgPath(fileUrl);

                    face.setFaceId(faceId);
                    faceService.insertFace(face);
                }
            }else {
                System.out.println("要确保网络和你的百度人脸检测应用ID是正常的哦");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code","400");
            map.put("msg", "文件上传失败!");
            map.put("url", fileUrl);
        }
        return map;
    }
}
