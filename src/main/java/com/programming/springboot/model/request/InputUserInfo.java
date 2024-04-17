package com.programming.springboot.model.request;

import com.programming.springboot.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InputUserInfo {

    @NotBlank private String name;

    @NotBlank private String username;

    @NotBlank private String password;

    @NotBlank private Role role;
}
