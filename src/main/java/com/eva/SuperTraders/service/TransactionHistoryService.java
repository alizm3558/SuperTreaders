package com.eva.SuperTraders.service;

import com.eva.SuperTraders.domain.dto.ShareDto;
import com.eva.SuperTraders.domain.dto.TransactionHistoryDto;

import java.util.List;

public interface TransactionHistoryService {


    TransactionHistoryDto save(TransactionHistoryDto transactionHistoryDto);

    TransactionHistoryDto update(TransactionHistoryDto transactionHistoryDto);

    TransactionHistoryDto getById(Long id);

    List<TransactionHistoryDto> getAll();

    void deleteById(Long id, Long deleteUserId);
}
