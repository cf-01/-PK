package com.cf.ssm.mapper;

import com.cf.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {


    User userLogin(@Param("username") String username, @Param("password") String password);

    int userRegister(@Param("username") String username, @Param("password") String password,@Param("email") String email,@Param("code")String code);

    User getEmailOne(String email);
}
