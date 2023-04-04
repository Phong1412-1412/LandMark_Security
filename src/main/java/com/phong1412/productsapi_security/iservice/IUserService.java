package com.phong1412.productsapi_security.iservice;

import com.phong1412.productsapi_security.Dto.LoginDto;
import com.phong1412.productsapi_security.Dto.UserRecord;
import com.phong1412.productsapi_security.Dto.UserWithRole;
import com.phong1412.productsapi_security.entities.User;

import java.util.List;

public interface IUserService {
    List<UserRecord> getAllUsers();
    User createUser(User user);
    User findUserById(String id);
    User findUserByName(String name);
    User UpdateUserById(User user);
    void deleteuser(String id);
}
