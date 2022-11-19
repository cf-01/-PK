package com.cf.ssm.service.impl;

import com.cf.ssm.mapper.ManagerMapper;
import com.cf.ssm.pojo.Manager;
import com.cf.ssm.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;


    public  Map<String,Object> login(String managerAccount, String managerPassword) {

        Map<String,Object> resultMap = new HashMap<String, Object>();

        Manager result =  managerMapper.login(managerAccount,managerPassword);
        if(result != null){
            resultMap.put("code",200);
            resultMap.put("message","登录成功");
        }else{
            resultMap.put("code",400);
            resultMap.put("message","登录失败");
        }
        return resultMap;

    }
}
