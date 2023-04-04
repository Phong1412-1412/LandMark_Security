package com.phong1412.productsapi_security.controller;

import com.phong1412.productsapi_security.Dto.ProvinceFamousPlaceDTO;
import com.phong1412.productsapi_security.entities.Famousplace;
import com.phong1412.productsapi_security.service.FamousPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/famousPlace")
public class FamousPlaceController {

    @Autowired
    private FamousPlaceService famousPlaceService;

    @GetMapping("/all")
    public ResponseEntity<List<Famousplace>> getAllFamous() {
        return ResponseEntity.ok().body(famousPlaceService.getAllFamousPlaces());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Famousplace> getFamousById(@PathVariable int id) {
        return ResponseEntity.ok().body(famousPlaceService.getFamousPlaceById(id).orElseThrow());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Famousplace>> getFamousByName(@RequestParam String famousname) {
        return ResponseEntity.ok().body(famousPlaceService.getFamousPlaceByName(famousname));
    }

    @GetMapping("/search/province")
    public ResponseEntity<List<ProvinceFamousPlaceDTO>> getFamousByProvinceName(@RequestParam String provinceName) {
        return ResponseEntity.ok().body(famousPlaceService.getFamousPlaceByProvinceName(provinceName));
    }

    @PostMapping("/add")
    public ResponseEntity<Famousplace> addUser(@RequestBody Famousplace famousplace) {
        return ResponseEntity.ok().body(famousPlaceService.createFamousPlace(famousplace));
    }

    @PutMapping("/update")
    public ResponseEntity<Famousplace> updateUser(@RequestBody Famousplace famousplace) {
        return ResponseEntity.ok().body(famousPlaceService.updateFamousPlace(famousplace));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        famousPlaceService.deleteFamousPlace(id);
        return ResponseEntity.ok().body("delete user successfully");
    }
}