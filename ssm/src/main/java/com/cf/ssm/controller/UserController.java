package com.cf.ssm.controller;

import com.cf.ssm.pojo.User;
import com.cf.ssm.service.UserService;
import com.cf.ssm.utils.JwtUtil;
import com.cf.ssm.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SendMail emailUtil;

    private static String Code;

    @PostMapping ("/userLogin")
    @CrossOrigin
    public Map<String,Object> userLogin(String username,String password){
        System.out.println(username+":"+password);
        return userService.userLogin(username, password);
    }

    @PostMapping ("/userRegister")
    @CrossOrigin
    public Map<String,Object> userRegister(User user){
        return userService.userRegister(user,Code);
    }

    @CrossOrigin
    @GetMapping("/getCode")
    @ResponseBody
    public String mail(String email) {
        //生成随机数
        String captcha = String.valueOf(new Random().nextInt(899999) + 100000);
        //发生邮件
        emailUtil.sendMail(email, "您的验证码为" + captcha + "(5分钟内有效)");
        //将验证码保存到Code中
        Code = captcha;
        //响应信息
        return captcha;
    }

    @PostMapping ("/getEmailOne")
    @CrossOrigin
    public Map<String,Object> getEmailOne(String email){
        return userService.getEmailOne(email);
    }

    @RequestMapping(path="/checkToken")
    @CrossOrigin
    public Boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }


}
