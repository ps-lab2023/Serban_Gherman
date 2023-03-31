package com.book.projectps.service.impl;


import com.book.projectps.model.Commission;
import com.book.projectps.repository.CommissionRepository;
import com.book.projectps.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {
    @Autowired
    private CommissionRepository commissionRepository;

    @Override
    public List<Commission> findAll() {
        return (List<Commission>) commissionRepository.findAll();
    }

    @Override
    public Commission findById(Long id) {
        return commissionRepository.findById(id).orElse(null);
    }

    @Override
    public Commission save(Commission commission) {
        return commissionRepository.save(commission);
    }

    @Override
    public void deleteById(Long id) {
        commissionRepository.deleteById(id);
    }
}