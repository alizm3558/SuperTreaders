package com.eva.SuperTraders.service.impl;

import com.eva.SuperTraders.domain.dto.TransactionHistoryDto;
import com.eva.SuperTraders.domain.entity.TransactionHistory;
import com.eva.SuperTraders.repository.TransactionHistoryRepository;
import com.eva.SuperTraders.service.TransactionHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;


    @Override
    public TransactionHistoryDto save(TransactionHistoryDto transactionHistoryDto) {
        transactionHistoryDto.setIsDeleted(Boolean.FALSE);
        return this.modelMapper.map(
                this.transactionHistoryRepository.save(
                        this.modelMapper.map(transactionHistoryDto, TransactionHistory.class)
                ), TransactionHistoryDto.class
        );
    }

    @Override
    public TransactionHistoryDto update(TransactionHistoryDto transactionHistoryDto) {
        TransactionHistoryDto oldDto = this.getById(transactionHistoryDto.getId());

        oldDto.setType(transactionHistoryDto.getType());
        oldDto.setPrice(transactionHistoryDto.getPrice());
        oldDto.setQuantity(transactionHistoryDto.getQuantity());
        oldDto.setUser(transactionHistoryDto.getUser());
        oldDto.setUpdateDate(new Date());
        oldDto.setUpdateUserId(transactionHistoryDto.getUpdateUserId());
        oldDto.setIsDeleted(transactionHistoryDto.getIsDeleted());
        return this.modelMapper.map(
                this.transactionHistoryRepository.save(
                        this.modelMapper.map(
                                oldDto,
                                TransactionHistory.class
                        )
                ),
                TransactionHistoryDto.class
        );
    }

    @Override
    public TransactionHistoryDto getById(Long id) {
        Optional<TransactionHistory> transactionHistoryOptional =transactionHistoryRepository.findById(id);
        if(transactionHistoryOptional.isPresent()){
            TransactionHistory transactionHistory = transactionHistoryOptional.get();
            return modelMapper.map(transactionHistory,TransactionHistoryDto.class);
        }
        else {
            return null;
        }
    }

    @Override
    public List<TransactionHistoryDto> getAll() {
        return this.transactionHistoryRepository.findAll().stream()
                .map(entity -> this.modelMapper.map(entity, TransactionHistoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id, Long deleteUserId) {
        TransactionHistoryDto dto = this.getById(id);
        dto.setIsDeleted(Boolean.TRUE);
        dto.setDeleteDate(new Date());
        dto.setDeleteUserId(deleteUserId);
        dto.setDeleteUserId(deleteUserId);
        update(dto);
    }
}
