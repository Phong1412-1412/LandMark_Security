package com.phong1412.productsapi_security.iservice;

import com.phong1412.productsapi_security.Dto.UserRecord;
import com.phong1412.productsapi_security.Dto.UserUpdate;
import com.phong1412.productsapi_security.entities.User;

import java.util.List;

public interface IUserService {
    List<UserRecord> getAllUsers();

    User createUser(User user);

    User findUserById(String id);

    User findUserByName(String name);

    User UpdateUserById(User user);

    User updateUserByName(UserUpdate userUpdate);

    void deleteuser(String id);
}
