package com.clone.backend.uber.dto;

import com.clone.backend.uber.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private Set<Role> roles;
}
