package com.phong1412.productsapi_security.iservice;

import com.phong1412.productsapi_security.entities.Province;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProvinceService {
    List<Province> getAllProvice();

    Province createProvice(Province province);

    Province updateProvice(Province province);

    void deleteProvice(int id);
}
