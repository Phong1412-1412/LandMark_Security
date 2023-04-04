package com.phong1412.productsapi_security.iservice;

import com.phong1412.productsapi_security.entities.Famousplace;

import java.util.List;
import java.util.Optional;

public interface IFamousPlaceService {
    List<Famousplace> getAllFamousPlaces();
    List<Famousplace> getFamousPlaceByName(String nameplace);
    Optional<Famousplace> getFamousPlaceById(int id);
    Famousplace createFamousPlace(Famousplace famousPlace);

    Famousplace updateFamousPlace(Famousplace famousPlacer);
    void deleteFamousPlace(int id);
}
