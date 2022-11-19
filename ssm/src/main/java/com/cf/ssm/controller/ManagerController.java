package com.cf.ssm.controller;

import com.cf.ssm.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/login")
    public Map<String,Object> login(String managerAccount,String managerPassword){
        return managerService.login(managerAccount, managerPassword);
    }


}
