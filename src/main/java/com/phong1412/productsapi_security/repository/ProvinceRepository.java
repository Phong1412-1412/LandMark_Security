package com.phong1412.productsapi_security.repository;

import com.phong1412.productsapi_security.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository
        extends JpaRepository<Province, Integer> {
    Optional<Province> findAllById(int id);

    Optional<Province> findProviceByProvinceName(String provinceName);

}
