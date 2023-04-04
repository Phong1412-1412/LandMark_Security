package com.phong1412.productsapi_security.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatesDto {
    private final double latitude;
    private final double longitude;

    public CoordinatesDto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
