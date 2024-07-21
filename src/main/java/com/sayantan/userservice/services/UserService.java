package com.sayantan.userservice.services;

import com.sayantan.userservice.exceptions.DupliateEmailException;
import com.sayantan.userservice.exceptions.IncorrectPasswordException;
import com.sayantan.userservice.exceptions.InvalidTokenException;
import com.sayantan.userservice.exceptions.UserNotFoundException;
import com.sayantan.userservice.models.Token;
import com.sayantan.userservice.models.User;
import com.sayantan.userservice.repositories.TokenRepository;
import com.sayantan.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, TokenRepository tokenRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }
    public User signup(String name, String email, String password) {
        if(userFoundByEmail(email))
            throw new DupliateEmailException("User already present wit this email " + email);
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPasswordHash(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    public Token login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) throw new UserNotFoundException("User not found with email " + email);
        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPasswordHash()))
            throw new IncorrectPasswordException("Password doesn't match for email " + email);
        return tokenRepository.save(genarateToken(user));
    }

    public User validate(String tokenString) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeletedAndExpiresAtIsGreaterThanEqual(tokenString, false, new Date());
        if(optionalToken.isEmpty())
            throw new InvalidTokenException("Provided token is not valid!");
        Token token = optionalToken.get();
        return token.getUser();
    }

    public void logout(String tokenString) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeletedAndExpiresAtIsGreaterThanEqual(tokenString, false, new Date());
        if(optionalToken.isEmpty())
            throw new InvalidTokenException("Provided token is not valid!");
        Token token = optionalToken.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }

    private boolean userFoundByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }

    private Token genarateToken(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 90);
        Date expiresAt = calendar.getTime();
        return new Token(RandomStringUtils.randomAlphabetic(128), user, expiresAt);
    }
}
