package com.programming.springboot.controller;

import com.programming.springboot.model.request.InputUserAuthenticate;
import com.programming.springboot.model.request.InputUserInfo;
import com.programming.springboot.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    public static final String AUTH_BASE_PATH = "/auth";
    public static final String REGISTER_PATH = AUTH_BASE_PATH + "/register";
    public static final String LOGIN_PATH = AUTH_BASE_PATH + "/login";

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(path = REGISTER_PATH , consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> register(@RequestBody @Valid InputUserInfo inputUserInfo) {
        return authenticationService.register(inputUserInfo);
    }

    @PostMapping(path = LOGIN_PATH , consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> login(@RequestBody @Valid InputUserAuthenticate inputUserAuthenticate) {
        return authenticationService.authenticate(inputUserAuthenticate);
    }

}
