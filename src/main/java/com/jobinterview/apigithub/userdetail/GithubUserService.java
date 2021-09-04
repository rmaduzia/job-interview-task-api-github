package com.jobinterview.apigithub.userdetail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static com.jobinterview.apigithub.userdetail.ExceptionMessage.ERROR_WHILE_PARSING_JSON;

@RequiredArgsConstructor
@Service
public class GithubUserService {

    private static final String basicUrl = "https://api.github.com/users/%s";

    RestTemplate restTemplate = new RestTemplateBuilder()
            .errorHandler(new RestTemplateResponseErrorHandler())
            .build();

    private final UserRepository userRepository;

    UserDetailsDto getUserDetail(String userLogin) {

        String url = addUserLoginIntoUrl(userLogin);

        JsonNode jsonNode =  restTemplate.getForEntity(url, JsonNode.class).getBody();

        if (jsonNode == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_WHILE_PARSING_JSON.name());
        }

        userRepository.insertOrUpdateIfUserLoginExists(userLogin);

        return UserDetailMapper.userDetailsMapper(jsonNode);
    }

    private String addUserLoginIntoUrl(String userLogin) {
        return String.format(basicUrl, userLogin);
    }
}