package com.jobinterview.apigithub.userdetail;

public enum ExceptionMessage {

    USER_NOT_EXISTS("did not find login"),
    GITHUB_API_ISSUE("github api got issue and could not return user detail"),
    ERROR_WHILE_PARSING_JSON("Internal Error while parsing github APi response, please contact with us"),
    ERROR_WHILE_INSERT_TO_DATABASE("Internal Error while inserting date to database");

    private final String value;

    ExceptionMessage(String value) {
        this.value = value;
    }
}
