package com.eva.SuperTraders.controller;

import com.eva.SuperTraders.domain.dto.UserDto;
import com.eva.SuperTraders.repository.TransactionHistoryRepository;
import com.eva.SuperTraders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto UserDto) {
        return ResponseEntity.ok(userService.save(UserDto));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserDto UserDto) {
        return ResponseEntity.ok(userService.update(UserDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Long systemUserID=123L;
        this.userService.deleteById(id,systemUserID);
        return ResponseEntity.ok("Record deleted successfully");
    }
}
