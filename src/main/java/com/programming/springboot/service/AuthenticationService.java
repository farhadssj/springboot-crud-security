package com.programming.springboot.service;

import com.programming.springboot.model.RestApiResponse;
import com.programming.springboot.model.User;
import com.programming.springboot.model.request.InputUserAuthenticate;
import com.programming.springboot.model.request.InputUserInfo;
import com.programming.springboot.repositiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("AuthenticationService")
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<Object> register(InputUserInfo inputUserInfo) {
        // check if user already exist. if exist than authenticate the user
        if(userRepository.findByUsername(inputUserInfo.getUsername()).isPresent()) {
            return RestApiResponse.buildResponseWithError(HttpStatus.CONFLICT.value(), "User already exist", "User already exist");
        }
        User user = new User();
        user.setName(inputUserInfo.getName());
        user.setUsername(inputUserInfo.getUsername());
        user.setPassword(passwordEncoder.encode(inputUserInfo.getPassword()));
        user.setRole(inputUserInfo.getRole());

        user = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return RestApiResponse.buildResponseWithDetails(HttpStatus.CREATED.value(), "User registration is successful", jwtToken);
    }

    public ResponseEntity<Object> authenticate(InputUserAuthenticate inputUserAuthenticate) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        inputUserAuthenticate.getUsername(),
                        inputUserAuthenticate.getPassword()
                )
        );
        User user = userRepository.findByUsername(inputUserAuthenticate.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return RestApiResponse.buildResponseWithDetails(HttpStatus.OK.value(), "User Login is successful", jwtToken);
    }


}
