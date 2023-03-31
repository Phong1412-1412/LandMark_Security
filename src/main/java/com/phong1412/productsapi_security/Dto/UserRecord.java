package com.phong1412.productsapi_security.Dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public record UserRecord(String id, String name, String email, String role) {
}
