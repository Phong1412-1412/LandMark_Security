package com.phong1412.productsapi_security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "famousplace")
public class Famousplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_province;
    private String famous_name;
    @Column(name = "famous_address")
    private String address;
    private String description;
    private String image;
}
