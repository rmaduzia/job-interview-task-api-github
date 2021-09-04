package com.jobinterview.apigithub.userdetail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import static com.jobinterview.apigithub.userdetail.ExceptionMessage.ERROR_WHILE_INSERT_TO_DATABASE;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public void insertOrUpdateIfUserLoginExists(String userLogin) {

        String sql = "INSERT INTO users(\"LOGIN\", \"REQUEST_COUNT\") VALUES(?, 1) on conflict(\"LOGIN\")" +
                     "do update set \"REQUEST_COUNT\" = (select \"REQUEST_COUNT\" from users where \"LOGIN\"=?) +1";

        int resultOfInsert = jdbcTemplate.update(sql, userLogin, userLogin);

        if(resultOfInsert == 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_INSERT_TO_DATABASE.name());
        }
    }
}