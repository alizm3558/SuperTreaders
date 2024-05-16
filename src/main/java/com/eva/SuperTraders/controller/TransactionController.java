package com.eva.SuperTraders.controller;

import com.eva.SuperTraders.domain.dto.TransactionHistoryDto;
import com.eva.SuperTraders.repository.TransactionHistoryRepository;
import com.eva.SuperTraders.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionHistoryService transactionHistoryService;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @GetMapping
    public ResponseEntity<List<TransactionHistoryDto>> getAll() {
        return ResponseEntity.ok(this.transactionHistoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionHistoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.transactionHistoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionHistoryDto> save(@RequestBody TransactionHistoryDto TransactionHistoryDto) {
        return ResponseEntity.ok(transactionHistoryService.save(TransactionHistoryDto));
    }

    @PutMapping
    public ResponseEntity<TransactionHistoryDto> update(@RequestBody TransactionHistoryDto TransactionHistoryDto) {
        return ResponseEntity.ok(transactionHistoryService.update(TransactionHistoryDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Long systemUserID=123L;
        this.transactionHistoryService.deleteById(id,systemUserID);
        return ResponseEntity.ok("Record deleted successfully");
    }
}
