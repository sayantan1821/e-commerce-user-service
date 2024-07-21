package com.sayantan.userservice.repositories;

import com.sayantan.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Override
    Token save(Token token);
    Optional<Token> findByValueAndIsDeleted(String value, boolean isDeleted);
    Optional<Token> findByValueAndIsDeletedAndExpiresAtIsGreaterThanEqual(String value, boolean isDeleted, Date expiresAt);
}
