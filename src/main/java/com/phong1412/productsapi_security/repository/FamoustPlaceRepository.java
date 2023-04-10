package com.phong1412.productsapi_security.repository;

import com.phong1412.productsapi_security.Dto.ProvinceFamousPlaceDTO;
import com.phong1412.productsapi_security.entities.Famousplace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FamoustPlaceRepository extends JpaRepository<Famousplace, Integer> {
    @Query(value = "SELECT f from Famousplace f")
    Page<Famousplace> findAll(Pageable pageable);

    Optional<Famousplace> findById(int id);

    @Query(value = "SELECT f from Famousplace f where f.famous_name = :name")
    Optional<Famousplace> findFamousPlaceByName(@Param("name") String name);

    @Query(value = "SELECT new com.phong1412.productsapi_security.Dto" +
            ".ProvinceFamousPlaceDTO(f.id, p.provinceName, f.famous_name, f.address , f.description, f.image, c) " +
            "FROM Famousplace f, Province p, Coordinates c " +
            "WHERE f.id_province = p.id and f.id = c.famousplace and p.provinceName LIKE  concat('%', :provinceName, '%')")
    Optional<List<ProvinceFamousPlaceDTO>> getFamousPlaceByProvince(@Param(value = "provinceName") String provinceName);

    @Query(value = "SELECT new com.phong1412.productsapi_security.Dto" +
            ".ProvinceFamousPlaceDTO(f.id, p.provinceName, f.famous_name, f.address , f.description, f.image, c) " +
            "FROM Famousplace f, Province p, Coordinates c " +
            "WHERE f.id_province = p.id and f.id = c.famousplace")
    Optional<List<ProvinceFamousPlaceDTO>> getFamousPlaceByDetailsAll();

    @Query(value = "SELECT new com.phong1412.productsapi_security.Dto" +
            ".ProvinceFamousPlaceDTO(f.id, p.provinceName, f.famous_name, f.address , f.description, f.image, c) " +
            "FROM Famousplace f, Province p, Coordinates c " +
            "WHERE f.id_province = p.id and f.id = c.famousplace and f.famous_name like concat('%', :name,'%') ")
    Optional<List<ProvinceFamousPlaceDTO>> getFamousPlaceByDetailsByPlaceName(@Param(value = "name") String name);
}
