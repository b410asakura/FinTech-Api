package com20.fintechapi.service;

import com20.fintechapi.dto.authenticationDto.SignInRequest;
import com20.fintechapi.dto.authenticationDto.AuthenticationResponse;
import com20.fintechapi.dto.authenticationDto.SignUpRequest;


public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);

}
