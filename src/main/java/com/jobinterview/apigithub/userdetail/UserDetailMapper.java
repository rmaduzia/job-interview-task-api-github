package com.jobinterview.apigithub.userdetail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.jobinterview.apigithub.userdetail.UserDetailField.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetailMapper {

    public static UserDetailsDto userDetailsMapper(JsonNode jsonNode){
        return UserDetailsDto.builder()
                .id(jsonNode.get(ID.toValue()).asText())
                .login(jsonNode.get(LOGIN.toValue()).asText())
                .name(jsonNode.get(NAME.toValue()).asText())
                .type(jsonNode.get(TYPE.toValue()).asText())
                .avatarUrl(jsonNode.get(AVATAR_URL.toValue()).asText())
                .created_at(jsonNode.get(CREATED_AT.toValue()).asText())
                .calculations(Double.toString(getCalculation(jsonNode.get(FOLLOWERS.toValue()).asDouble(), jsonNode.get(PUBLIC_REPOS.toValue()).asDouble())))
                .build();
    }

    private static double getCalculation(double followers, double publicRepos) {
        double initialValue = 6.0;
        return initialValue / followers * (2 + publicRepos);
    }
}