package uk.co.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.blog.dtos.authors.CreateAuthorDTO;
import uk.co.blog.dtos.responses.LoginResponse;
import uk.co.blog.dtos.responses.RegisterResponse;
import uk.co.blog.services.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody CreateAuthorDTO createAuthorDTO){
        return userService.register(createAuthorDTO);
    }
    @PostMapping("/login")
    public LoginResponse login(Authentication authentication){
        return userService.login(authentication);
    }
}
