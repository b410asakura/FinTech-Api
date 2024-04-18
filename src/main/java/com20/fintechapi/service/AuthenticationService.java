package com20.fintechapi.service;

import com20.fintechapi.dto.authenticationDto.SignInRequest;
import com20.fintechapi.dto.authenticationDto.AuthenticationResponse;
import com20.fintechapi.dto.authenticationDto.SignUpRequest;
import com20.fintechapi.dto.authenticationDto.UserResponse;

import java.util.List;


public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);

    List<UserResponse> getAllUsers();
}
