package com.jobinterview.apigithub.userdetail;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static com.jobinterview.apigithub.userdetail.ExceptionMessage.GITHUB_API_ISSUE;
import static com.jobinterview.apigithub.userdetail.ExceptionMessage.USER_NOT_EXISTS;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, USER_NOT_EXISTS.name());

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
                || httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, GITHUB_API_ISSUE.name());
    }
}