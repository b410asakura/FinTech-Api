package com20.fintechapi.repository.dao;

import com20.fintechapi.dto.authenticationDto.UserResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<UserResponse> getAll();

}
