package com.phong1412.productsapi_security.service;

import com.phong1412.productsapi_security.controller.FamousPlaceController;
import com.phong1412.productsapi_security.entities.Famousplace;
import com.phong1412.productsapi_security.exception.BadException;
import com.phong1412.productsapi_security.exception.NotFoundException;
import com.phong1412.productsapi_security.iservice.IFamousPlace;
import com.phong1412.productsapi_security.repository.FamoustPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamousPlaceService implements IFamousPlace {

    private final FamoustPlaceRepository famoustPlaceRepository;
    @Override
    public List<Famousplace> getAllFamousPlaces() {
        return famoustPlaceRepository.findAll();
    }

    @Override
    public List<Famousplace> getFamousPlaceByName(String nameplace) {
        List<Famousplace> famousplaceList =  famoustPlaceRepository.findAll().stream().filter(famousplace -> famousplace.getFamous_name().contains(nameplace)).toList();
        if(famousplaceList.stream().count() > 0) {
            return famousplaceList;
        }
       throw new BadException("Không tìm thấy địa danh nổi tiếng có tên: "+nameplace);
    }

    @Override
    public Optional<Famousplace> getFamousPlaceById(int id) {
        if(famoustPlaceRepository.findById(id).isPresent()) {
            return famoustPlaceRepository.findById(id);
        }
        throw new BadException("Không tìm thấy địa danh có id: "+ id);
    }

    @Override
    public Famousplace createFamousPlace(Famousplace famousPlace) {
        Famousplace check = famoustPlaceRepository.findFamousPlaceByName(famousPlace.getFamous_name()).orElse(null);
        if(check==null)
        {
            famoustPlaceRepository.save(famousPlace);
            return famousPlace;
        }
        throw new BadException("Địa danh đã tồn tại trong database!!!");
    }

    @Override
    public Famousplace updateFamousPlace(Famousplace famousPlacer) {
        if(famoustPlaceRepository.findById(famousPlacer.getId()).isPresent()) {
            return famoustPlaceRepository.save(famousPlacer);
        }
        throw new BadException("Không tìm thấy địa danh có id: "+famousPlacer.getId() +" để cập nhật" );
    }

    @Override
    public void deleteFamousPlace(int id) {
        if(!famoustPlaceRepository.findById(id).isPresent()) {
            throw new BadException("Không tìm thấy địa danh có id: "+id+" để xóa" );
        }
        famoustPlaceRepository.delete(famoustPlaceRepository.findById(id).get());
    }

}