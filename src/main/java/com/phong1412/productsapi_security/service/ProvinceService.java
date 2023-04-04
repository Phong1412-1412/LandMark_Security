package com.phong1412.productsapi_security.service;

import com.phong1412.productsapi_security.entities.Province;
import com.phong1412.productsapi_security.exception.BadException;
import com.phong1412.productsapi_security.exception.NotFoundException;
import com.phong1412.productsapi_security.iservice.IProvinceService;
import com.phong1412.productsapi_security.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService implements IProvinceService {
    private final ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvice() {
        return provinceRepository.findAll();
    }

    public Province getOneProvinceByName(String provinceName) {
        if (provinceRepository.findProviceByProvinceName(provinceName).isPresent()) {
            return provinceRepository.findProviceByProvinceName(provinceName).get();
        }
        throw new NotFoundException("Không tìm thấy tỉnh thành có tên: " + provinceName);
    }

    @Override
    public Province createProvice(Province province) {
        Province check = provinceRepository.findProviceByProvinceName(province.getProvinceName()).orElse(null);
        if (check == null) {
            return provinceRepository.save(province);
        }
        throw new BadException("Tỉnh thành đã tồn tại trong database!!!");
    }

    @Override
    public Province updateProvice(Province province) {
        if (provinceRepository.findById(province.getId()).isPresent()) {
            return provinceRepository.save(province);
        }
        throw new BadException("Không tìm thấy tỉnh thành có id: " + province.getId() + " để cập nhật");
    }

    @Override
    public void deleteProvice(int id) {
        if (!provinceRepository.findById(id).isPresent()) {
            throw new BadException("Không tìm thấy địa danh có id: " + id + " để xóa");
        }
        provinceRepository.delete(provinceRepository.findById(id).get());
    }
}
