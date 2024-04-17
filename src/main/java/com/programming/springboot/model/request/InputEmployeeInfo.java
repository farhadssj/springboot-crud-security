package com.programming.springboot.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InputEmployeeInfo{
    @NotBlank(message = "Field is mandatory") private String name;

    @NotBlank private int age;

    @NotBlank private String department;

    @NotBlank private int salary;
}
