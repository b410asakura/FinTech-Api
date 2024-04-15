package com20.fintechapi.dto.authenticationDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
}
