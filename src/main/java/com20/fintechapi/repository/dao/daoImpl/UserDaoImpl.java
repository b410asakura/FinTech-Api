package com20.fintechapi.repository.dao.daoImpl;

import com20.fintechapi.dto.authenticationDto.UserResponse;
import com20.fintechapi.enums.Role;
import com20.fintechapi.repository.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<UserResponse> getAll() {
        String sql = """
                select u.first_name, u.last_name, u.email, u.password, u.role
                           from users u
                """;
        return jdbcTemplate.query(sql,( (rs, rowNum) -> {
            UserResponse user = new UserResponse();
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            return user;
        }));
    }
}
