package com20.fintechapi.dto.authenticationDto;

import com20.fintechapi.enums.Role;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
