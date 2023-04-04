package com.phong1412.productsapi_security.controller;

import com.phong1412.productsapi_security.entities.Province;
import com.phong1412.productsapi_security.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provinces")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/all")
    public ResponseEntity<List<Province>> getAllProvice() {
        return ResponseEntity.ok().body(provinceService.getAllProvice());
    }


    @GetMapping("/search")
    public ResponseEntity<Province> getProvinceByName(@RequestParam String provincename) {
        return ResponseEntity.ok().body(provinceService.getOneProvinceByName(provincename));
    }

    @PostMapping("/add")
    public ResponseEntity<Province> addProvice(@RequestBody Province province) {
        return ResponseEntity.ok().body(provinceService.createProvice(province));
    }

    @PutMapping("/update")
    public ResponseEntity<Province> updateProvice(@RequestBody Province province) {
        return ResponseEntity.ok().body(provinceService.updateProvice(province));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProvice(@PathVariable int id) {
        provinceService.deleteProvice(id);
        return ResponseEntity.ok().body("delete user successfully");
    }
}
