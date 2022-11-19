package com.cf.ssm.mapper;

import com.cf.ssm.pojo.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerMapper {

    Manager login(@Param("managerAccount") String managerAccount,@Param("managerPassword") String managerPassword);
}
