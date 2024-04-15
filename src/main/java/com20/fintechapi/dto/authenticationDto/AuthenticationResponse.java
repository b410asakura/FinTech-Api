package com20.fintechapi.dto.authenticationDto;

import com20.fintechapi.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AuthenticationResponse {
    private Long id;
    private String token;
    private String email;
    private Role role;
}
