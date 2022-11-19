package com.cf.ssm.service;

import java.util.Map;

public interface ManagerService {

    Map<String,Object> login(String managerAccount, String managerPassword);
}
