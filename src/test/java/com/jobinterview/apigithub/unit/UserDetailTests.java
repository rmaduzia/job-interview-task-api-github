package com.jobinterview.apigithub.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobinterview.apigithub.userdetail.UserDetailsDto;
import com.jobinterview.apigithub.userdetail.UserDetailMapper;
import org.junit.jupiter.api.Test;

import static com.jobinterview.apigithub.userdetail.UserDetailField.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDetailTests {

    @Test
    void shouldMapUserDetails() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(getUserDetailAsJson());

        UserDetailsDto userDetailsDto= UserDetailMapper.userDetailsMapper(jsonNode);

        assertEquals(userDetailsDto.getId(), "47938303");
        assertEquals(userDetailsDto.getLogin(), "rmaduzia");
        assertEquals(userDetailsDto.getName(), "null");
        assertEquals(userDetailsDto.getType(), "User");
        assertEquals(userDetailsDto.getAvatarUrl(), "https://avatars.githubusercontent.com/u/47938303?v=4");
        assertEquals(userDetailsDto.getCreated_at(), "2019-02-24T08:14:23Z");
        assertEquals(userDetailsDto.getCalculations(), "84.0");
    }

    @Test
    void shouldGetUserDetailsFromJsonNode() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(getUserDetailAsJson());

        assertEquals(jsonNode.get(ID.toValue()).asText(), "47938303");
        assertEquals(jsonNode.get(LOGIN.toValue()).asText(), "rmaduzia");
        assertEquals(jsonNode.get(NAME.toValue()).asText(), "null");
        assertEquals(jsonNode.get(TYPE.toValue()).asText(), "User");
        assertEquals(jsonNode.get(AVATAR_URL.toValue()).asText(), "https://avatars.githubusercontent.com/u/47938303?v=4");
        assertEquals(jsonNode.get(CREATED_AT.toValue()).asText(), "2019-02-24T08:14:23Z");
        assertEquals(jsonNode.get(FOLLOWERS.toValue()).asDouble(), 1.0);
        assertEquals(jsonNode.get(PUBLIC_REPOS.toValue()).asDouble(), 12.0);
    }

    public static String getUserDetailAsJson() {

        return "{\n" +
                "  \"login\": \"rmaduzia\",\n" +
                "  \"id\": 47938303,\n" +
                "  \"node_id\": \"MDQ6VXNlcjQ3OTM4MzAz\",\n" +
                "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/47938303?v=4\",\n" +
                "  \"gravatar_id\": \"\",\n" +
                "  \"url\": \"https://api.github.com/users/rmaduzia\",\n" +
                "  \"html_url\": \"https://github.com/rmaduzia\",\n" +
                "  \"followers_url\": \"https://api.github.com/users/rmaduzia/followers\",\n" +
                "  \"following_url\": \"https://api.github.com/users/rmaduzia/following{/other_user}\",\n" +
                "  \"gists_url\": \"https://api.github.com/users/rmaduzia/gists{/gist_id}\",\n" +
                "  \"starred_url\": \"https://api.github.com/users/rmaduzia/starred{/owner}{/repo}\",\n" +
                "  \"subscriptions_url\": \"https://api.github.com/users/rmaduzia/subscriptions\",\n" +
                "  \"organizations_url\": \"https://api.github.com/users/rmaduzia/orgs\",\n" +
                "  \"repos_url\": \"https://api.github.com/users/rmaduzia/repos\",\n" +
                "  \"events_url\": \"https://api.github.com/users/rmaduzia/events{/privacy}\",\n" +
                "  \"received_events_url\": \"https://api.github.com/users/rmaduzia/received_events\",\n" +
                "  \"type\": \"User\",\n" +
                "  \"site_admin\": false,\n" +
                "  \"name\": null,\n" +
                "  \"company\": null,\n" +
                "  \"blog\": \"\",\n" +
                "  \"location\": null,\n" +
                "  \"email\": null,\n" +
                "  \"hireable\": null,\n" +
                "  \"bio\": null,\n" +
                "  \"twitter_username\": null,\n" +
                "  \"public_repos\": 12,\n" +
                "  \"public_gists\": 1,\n" +
                "  \"followers\": 1,\n" +
                "  \"following\": 1,\n" +
                "  \"created_at\": \"2019-02-24T08:14:23Z\",\n" +
                "  \"updated_at\": \"2021-09-02T17:29:08Z\"\n" +
                "}";
    }
}