package com.bugtracker.controller;

import com.bugtracker.dto.BugRequestDTO;
import com.bugtracker.dto.BugResponseDTO;
import com.bugtracker.service.BugService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bugs")
public class Bugcontroller {
    private final BugService bs;

    public Bugcontroller(BugService bs) {
        this.bs = bs;
    }

    @PostMapping
    public ResponseEntity<BugResponseDTO> createbug(@Valid @RequestBody BugRequestDTO bugRequestDTO) {
        return ResponseEntity.ok(bs.createbug(bugRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<BugResponseDTO>> getallbugs() {
        return ResponseEntity.ok(bs.getallbugs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BugResponseDTO> getbugbyid(@PathVariable Long id) {
        return ResponseEntity.ok(bs.getbugbyid(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletebug(@PathVariable Long id) {
        return ResponseEntity.ok(bs.deletebug(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BugResponseDTO> updatebug(@PathVariable Long id, @Valid @RequestBody BugRequestDTO update) {
         return ResponseEntity.ok(bs.updatebug(id, update));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<BugResponseDTO>> pagination(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(bs.pagination(page, size));
    }
}
