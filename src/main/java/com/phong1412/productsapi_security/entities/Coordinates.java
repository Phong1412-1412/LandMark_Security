package com.phong1412.productsapi_security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "coordinates")
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_famousplace")
    private int famousplace;
    private double latitude;
    private double longitude;
}
