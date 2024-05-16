package com.eva.SuperTraders.repository;

import com.eva.SuperTraders.domain.entity.Share;
import com.eva.SuperTraders.domain.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findAll();

    Optional<TransactionHistory> getTransactionHistoryById(Long id);
}
