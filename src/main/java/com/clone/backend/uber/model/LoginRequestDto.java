package com.clone.backend.uber.model;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
