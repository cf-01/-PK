package com.cf.ssm.service;

import com.cf.ssm.pojo.User;

import java.util.Map;

public interface UserService {


    Map<String, Object> userLogin(String username, String password);

    Map<String, Object> userRegister(User user,String code);

    Map<String, Object> getEmailOne(String email);
}
