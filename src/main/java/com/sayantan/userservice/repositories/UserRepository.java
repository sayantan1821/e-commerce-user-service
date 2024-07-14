package com.sayantan.userservice.repositories;

import com.sayantan.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user);
}
