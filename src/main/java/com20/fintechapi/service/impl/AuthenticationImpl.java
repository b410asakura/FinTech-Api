package com20.fintechapi.service.impl;

import com20.fintechapi.config.jwtConfig.JwtService;
import com20.fintechapi.dto.authenticationDto.AuthenticationResponse;
import com20.fintechapi.dto.authenticationDto.SignInRequest;
import com20.fintechapi.dto.authenticationDto.SignUpRequest;
import com20.fintechapi.dto.authenticationDto.UserResponse;
import com20.fintechapi.entity.User;
import com20.fintechapi.enums.Role;
import com20.fintechapi.globalException.AlreadyExistsException;
import com20.fintechapi.globalException.BadCredentialException;
import com20.fintechapi.repository.UserRepository;
import com20.fintechapi.repository.dao.UserDao;
import com20.fintechapi.service.AuthenticationService;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Builder
@Slf4j
public class AuthenticationImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistsException(
                    String.format("Already exist user with email: %s", signUpRequest.getEmail())
            );
        }
        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        log.info("User saved");
        String token = jwtService.generateToken(user);
        log.info("user token generated");
        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail())
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("User with email: %s", signInRequest.getEmail())
                        )
                );
        if (signInRequest.getEmail().isBlank()) {
            log.error("Email is blank");
            throw new BadCredentialException("Email is blank");
        }
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.error("wrong password");
            log.error(String.format("request password %s", signInRequest.getPassword()));
            log.error("user old password" + user.getPassword());
            throw new BadCredentialException("Wrong password");
        }
        String token = jwtService.generateToken(user);
        log.info("Token generated");
        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userDao.getAll();
    }

    @PostConstruct
    public void initSaveAdmin() {
        User user = User.builder()
                .firstName("admin")
                .lastName("admins lastName")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .build();
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
        log.info("Admin successfully created and saved");
    }
}
