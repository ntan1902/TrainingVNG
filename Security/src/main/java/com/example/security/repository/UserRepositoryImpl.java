package com.example.security.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserDetails> findByUsername(String username) {
        String sql = "select username, password from users where username=?";
        Object[] params = new Object[]{username};

        RowMapper<UserDetails> mapper = (rs, rowNum) -> {
            String username1 = rs.getString(1);
            String password = rs.getString(2);
            return new User(username1, password, true, true, true, true, AuthorityUtils.NO_AUTHORITIES);
        };
        UserDetails user = jdbcTemplate.queryForObject(sql, mapper, params);

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<UserDetails> insert(UserDetails user) {
        String sql = "insert into users(username, `password`) values(:username, :password)";

        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        NamedParameterJdbcOperations template = new NamedParameterJdbcTemplate(jdbcTemplate);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int key = template.update(sql, params, keyHolder);
        if (key != 0) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
