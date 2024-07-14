package com.sayantan.userservice.controllers;

import com.sayantan.userservice.dtos.*;
import com.sayantan.userservice.exceptions.DupliateEmailException;
import com.sayantan.userservice.exceptions.IncorrectPasswordException;
import com.sayantan.userservice.exceptions.UserNotFoundException;
import com.sayantan.userservice.models.Token;
import com.sayantan.userservice.models.User;
import com.sayantan.userservice.services.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO req) {
        try {
            User user = userService.signup(req.getName(), req.getEmail(), req.getPassword());
            return new ResponseEntity<>(UserDTO.form(user), HttpStatus.OK);
        } catch(DupliateEmailException err) {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(err.getMessage(), "");
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO req) {
        try {
            Token token = userService.login(req.getEmail(), req.getPassword());
            return new ResponseEntity<>(TokenResponseDTO.form(token), HttpStatus.OK);
        } catch (IncorrectPasswordException | UserNotFoundException err) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(err.getMessage(), "");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException err) {
            System.out.println(err.getMessage());
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(err.getMessage(), getStackTraceAsString(err));
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequestDTO req) {
//        return null;
    }

    @GetMapping("/validate/{token}")
    public UserDTO validateToken(@PathVariable String token) {
        return null;
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
