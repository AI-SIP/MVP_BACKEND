package com.aisip.OnO.backend.repository;

import com.aisip.OnO.backend.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByIdentifier(String identifier);
}
