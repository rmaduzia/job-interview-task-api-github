package com.jobinterview.apigithub.userdetail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class GithubUserController {

    private final GithubUserService githubUserService;

    @GetMapping("/{userLogin}")
    UserDetailsDto getUserDetail(@PathVariable String userLogin) {

        return githubUserService.getUserDetail(userLogin);
    }
}