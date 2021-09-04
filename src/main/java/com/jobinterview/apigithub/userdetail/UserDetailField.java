package com.jobinterview.apigithub.userdetail;

public enum UserDetailField {

    ID("id"),
    LOGIN("login"),
    NAME("name"),
    TYPE("type"),
    AVATAR_URL("avatar_url"),
    CREATED_AT("created_at"),
    FOLLOWERS("followers"),
    PUBLIC_REPOS("public_repos");

    private final String value;

    UserDetailField(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }
}
