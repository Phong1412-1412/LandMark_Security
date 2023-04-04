package com.phong1412.productsapi_security.Dto;

import com.phong1412.productsapi_security.entities.Coordinates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProvinceFamousPlaceDTO {
    private int id;
    private String provinceName;
    private String famousName;
    private String address;
    private String description;
    private String image;
    private CoordinatesDto coordinates;

    public ProvinceFamousPlaceDTO(int id, String provinceName, String famousName, String address, String description, String image, Coordinates coordinates) {
        this.id = id;
        this.provinceName = provinceName;
        this.famousName = famousName;
        this.address = address;
        this.description = description;
        this.image = image;
        this.coordinates = new CoordinatesDto(coordinates.getLatitude(), coordinates.getLongitude());
    }
}
