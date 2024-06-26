package org.example.springboard.repository;

import org.example.springboard.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    Optional<UserAccount> findByUsername(String username);
}
