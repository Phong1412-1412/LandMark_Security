package com.phong1412.productsapi_security.Dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {
    //it's a Data Trasfer Object for Login
    private String name ;
    private String password ;
}
