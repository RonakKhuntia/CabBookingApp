package com.clone.backend.uber.repository;

import com.clone.backend.uber.entity.User;
import com.clone.backend.uber.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
