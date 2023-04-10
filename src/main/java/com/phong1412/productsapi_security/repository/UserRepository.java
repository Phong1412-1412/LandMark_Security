package com.phong1412.productsapi_security.repository;

import com.phong1412.productsapi_security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByUsername(String name);

    @Query(value = "SELECT u FROM  User u where u.useraccount = :account")
    Optional<User> findUserByUseraccount(@Param("account") String account);

    @Query(value = "SELECT u FROM  User u where u.id = :id")
    Optional<User> findUsersById(@Param("id") String id);
}
