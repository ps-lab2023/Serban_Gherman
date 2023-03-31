package com.book.projectps.controller;

import com.book.projectps.model.Commission;
import com.book.projectps.service.impl.CommissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commissions")
public class CommissionController {
    @Autowired
    private CommissionServiceImpl commissionService;

    // ... other methods

    @GetMapping("/{id}")
    public ResponseEntity<Commission> findById(@PathVariable Long id) {
        Commission commission = commissionService.findById(id);
        return commission != null ? ResponseEntity.ok(commission) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Commission> save(@RequestBody Commission commission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commissionService.save(commission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commission> update(@PathVariable Long id, @RequestBody Commission commission) {
        if (!id.equals(commission.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(commissionService.save(commission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commissionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}