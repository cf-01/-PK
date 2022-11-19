package com.cf.ssm.service.impl;

import com.cf.ssm.mapper.UserMapper;
import com.cf.ssm.pojo.User;
import com.cf.ssm.service.UserService;
import com.cf.ssm.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> userLogin(String username, String password) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        User result =  userMapper.userLogin(username,password);
        if(result != null){
            result.setToken(JwtUtil.createToken());
            resultMap.put("token",result.getToken());
            resultMap.put("code",200);
            resultMap.put("message","登录成功");
            resultMap.put("userId",result.getId());
        }else{
            resultMap.put("code",400);
            resultMap.put("message","登录失败");
            resultMap.put("token",null);
        }
        return resultMap;
    }


    public Map<String, Object> userRegister(User user,String captcha) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String code = user.getCode();
        System.out.println(code);
        System.out.println(captcha);
        // 对比验证码是否正确
        if (code.equals(captcha)){
            int result =  userMapper.userRegister(user.getUsername(),user.getPassword(),user.getEmail(),code);
            if(result > 0){
                resultMap.put("code",200);
                resultMap.put("message","注册成功");
            }else{
                resultMap.put("code",400);
                resultMap.put("message","注册失败");
            }
        }
        else {
            resultMap.put("code",400);
            resultMap.put("message","验证码错误");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getEmailOne(String email) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        User result =  userMapper.getEmailOne(email);
        if(result != null){
            resultMap.put("code",200);
            resultMap.put("message","发送成功");
        }else{
            resultMap.put("code",400);
            resultMap.put("message","是否注册一个新账号");
        }
        return resultMap;
    }

}
