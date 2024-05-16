package com.eva.SuperTraders.repository;

import com.eva.SuperTraders.domain.entity.Share;
import com.eva.SuperTraders.domain.entity.TransactionHistory;
import com.eva.SuperTraders.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> getTransactionHistoryById(Long id);
}
