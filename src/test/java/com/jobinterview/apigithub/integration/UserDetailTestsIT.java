package com.jobinterview.apigithub.integration;

import com.jobinterview.apigithub.userdetail.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;

import static com.jobinterview.apigithub.userdetail.ExceptionMessage.USER_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableAutoConfiguration
@ActiveProfiles("testcontainers")
@Testcontainers
@Tag("integration")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDetailTestsIT {


    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    private static final String getUserDetailApiUrl = "/users/";
    private static final String userLoginWhichOneNotExists = "someWrongLogin7345636346";
    private static final String userLoginWhichExists = "rmaduzia";

    // TODO Create user with constant data and then replace name into variable $userLoginWhichExists

    @Test
    @Order(1)
    public void getUserFromGithubApi() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(getUserDetailApiUrl + userLoginWhichExists))
                .andExpect(status().isOk())
                .andReturn();

        String selectQuery = "select * from users WHERE \"LOGIN\" = ?";

        List<Map<String, Object>> tmp = jdbcTemplate.queryForList(selectQuery, userLoginWhichExists);

        assertEquals(getValidUserDetail(), mvcResult.getResponse().getContentAsString());
        assertEquals(1, tmp.size());
        assertEquals(userLoginWhichExists, tmp.get(0).get("LOGIN"));
        assertEquals(1, tmp.get(0).get("REQUEST_COUNT"));
    }

    @Test
    @Order(2)
    public void getUserFromGithubApiWhenUserAlreadyExistsInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(getUserDetailApiUrl + userLoginWhichExists))
                .andExpect(status().isOk())
                .andReturn();

        String selectQuery = "select * from users WHERE \"LOGIN\" = ?";

        List<Map<String, Object>> tmp = jdbcTemplate.queryForList(selectQuery, userLoginWhichExists);

        assertEquals(getValidUserDetail(), mvcResult.getResponse().getContentAsString());
        assertEquals(1, tmp.size());
        assertEquals(userLoginWhichExists, tmp.get(0).get("LOGIN"));
        assertEquals(2, tmp.get(0).get("REQUEST_COUNT"));
    }

    @Test
    public void throwExceptionWhenUserDoesNotExists() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(getUserDetailApiUrl + userLoginWhichOneNotExists))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(mvcResult.getResponse().getErrorMessage(),USER_NOT_EXISTS.name());

    }

    public static String getValidUserDetail() {
        return "{\"id\":\"47938303\",\"login\":\"rmaduzia\",\"name\":\"null\",\"type\":\"User\",\"avatarUrl\":\"https://avatars.githubusercontent.com/u/47938303?v=4\",\"created_at\":\"2019-02-24T08:14:23Z\",\"calculations\":\"84.0\"}";
    }


}
