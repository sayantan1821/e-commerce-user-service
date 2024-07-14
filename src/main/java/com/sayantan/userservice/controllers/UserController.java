package com.sayantan.userservice.controllers;

import com.sayantan.userservice.dtos.LoginRequestDTO;
import com.sayantan.userservice.dtos.LogoutRequestDTO;
import com.sayantan.userservice.dtos.SignupRequestDTO;
import com.sayantan.userservice.dtos.UserDTO;
import com.sayantan.userservice.models.Token;
import com.sayantan.userservice.models.User;
import com.sayantan.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public UserDTO signup(@RequestBody SignupRequestDTO req) {
        User user = userService.signup(req.getName(), req.getEmail(), req.getPassword());
        return UserDTO.form(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDTO req) {
        Token token = userService.login(req.getEmail(), req.getPassword());
        return null;
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequestDTO req) {
//        return null;
    }

    @GetMapping("/validate/{token}")
    public UserDTO validateToken(@PathVariable String token) {
        return null;
    }
}
