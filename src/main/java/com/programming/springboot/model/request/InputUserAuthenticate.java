package com.programming.springboot.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InputUserAuthenticate {

    @NotBlank private String username;

    @NotBlank private String password;
}
