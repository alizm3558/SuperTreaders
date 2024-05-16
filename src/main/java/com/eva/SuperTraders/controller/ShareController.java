package com.eva.SuperTraders.controller;

import com.eva.SuperTraders.domain.dto.ShareDto;
import com.eva.SuperTraders.repository.ShareRepository;
import com.eva.SuperTraders.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @Autowired
    private ShareRepository shareRepository;

    @GetMapping
    public ResponseEntity<List<ShareDto>> getAll() {
        return ResponseEntity.ok(this.shareService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShareDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.shareService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ShareDto> save(@RequestBody ShareDto shareDto) {
        return ResponseEntity.ok(shareService.save(shareDto));
    }

    @PutMapping
    public ResponseEntity<ShareDto> update(@RequestBody ShareDto shareDto) {
        return ResponseEntity.ok(shareService.update(shareDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Long systemUserID=123L;
        this.shareService.deleteById(id,systemUserID);
        return ResponseEntity.ok("Record deleted successfully");
    }
}
