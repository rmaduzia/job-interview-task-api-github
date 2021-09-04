package com.jobinterview.apigithub.userdetail;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class UserDetailsDto {

    private String id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String created_at;
    private String calculations;
}