package com.phong1412.productsapi_security.repository;

import com.phong1412.productsapi_security.controller.FamousPlaceController;
import com.phong1412.productsapi_security.entities.Famousplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FamoustPlaceRepository extends JpaRepository<Famousplace, Integer> {
    Optional<Famousplace> findById(int id);

    @Query(value = "SELECT f from Famousplace f where f.famous_name = :name")
    Optional<Famousplace> findFamousPlaceByName(@Param("name") String name);
}
