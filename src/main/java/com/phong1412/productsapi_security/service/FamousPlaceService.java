package com.phong1412.productsapi_security.service;

import com.phong1412.productsapi_security.Dto.ProvinceFamousPlaceDTO;
import com.phong1412.productsapi_security.entities.Famousplace;
import com.phong1412.productsapi_security.exception.BadException;
import com.phong1412.productsapi_security.iservice.IFamousPlaceService;
import com.phong1412.productsapi_security.repository.FamoustPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamousPlaceService implements IFamousPlaceService {

    private final FamoustPlaceRepository famoustPlaceRepository;

    @Override
    public List<Famousplace> getAllFamousPlaces() {
        return famoustPlaceRepository.findAll();
    }

    @Override
    public List<Famousplace> getFamousPlaceByName(String nameplace) {
        List<Famousplace> famousplaceList = famoustPlaceRepository.findAll().stream().filter(famousplace -> famousplace.getFamous_name().contains(nameplace)).toList();
        if (famousplaceList.stream().count() > 0) {
            return famousplaceList;
        }
        throw new BadException("The famous place with name could not be found: " + nameplace);
    }

    public List<ProvinceFamousPlaceDTO> getFamousPlaceByProvinceName(String provinceName) {
        Optional<List<ProvinceFamousPlaceDTO>> proPlaceDtos = famoustPlaceRepository.getFamousPlaceByProvince(provinceName);
        if (proPlaceDtos.isPresent()) {
            return proPlaceDtos.get();
        }
        throw new BadException("Can't find any famous place in province with name: " + provinceName);
    }

    public List<ProvinceFamousPlaceDTO> getFamousPlaceDetailsAll() {
        Optional<List<ProvinceFamousPlaceDTO>> proPlaceDtos = famoustPlaceRepository.getFamousPlaceByDetailsAll();
        if (proPlaceDtos.isPresent()) {
            return proPlaceDtos.get();
        }
        throw new BadException("Can't find any famous place!!");
    }

    public List<ProvinceFamousPlaceDTO> getFamousPlaceByDetailsByPlaceName(String name) {
        Optional<List<ProvinceFamousPlaceDTO>> proPlaceDtos = famoustPlaceRepository.getFamousPlaceByDetailsByPlaceName(name);
        if (proPlaceDtos.isPresent()) {
            return proPlaceDtos.get();
        }
        throw new BadException("Can't find any famous place with name: " + name);
    }

    @Override
    public Optional<Famousplace> getFamousPlaceById(int id) {
        if (famoustPlaceRepository.findById(id).isPresent()) {
            return famoustPlaceRepository.findById(id);
        }
        throw new BadException("Can't find any famous place id: " + id);
    }

    @Override
    public Famousplace createFamousPlace(Famousplace famousPlace) {
        Famousplace check = famoustPlaceRepository.findFamousPlaceByName(famousPlace.getFamous_name()).orElse(null);
        if (check == null) {
            famoustPlaceRepository.save(famousPlace);
            return famousPlace;
        }
        throw new BadException("The famous place already exists in database!!!");
    }

    @Override
    public Famousplace updateFamousPlace(Famousplace famousPlacer) {
        if (famoustPlaceRepository.findById(famousPlacer.getId()).isPresent()) {
            return famoustPlaceRepository.save(famousPlacer);
        }
        throw new BadException("Can't fina famous place id: " + famousPlacer.getId() + " to update!");
    }

    @Override
    public void deleteFamousPlace(int id) {
        if (!famoustPlaceRepository.findById(id).isPresent()) {
            throw new BadException("Can't find famous place with id: " + id + " to delete!");
        }
        famoustPlaceRepository.delete(famoustPlaceRepository.findById(id).get());
    }

    public Page<Famousplace> getAllFamousPlacePage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return famoustPlaceRepository.findAll(pageable);
    }
}
