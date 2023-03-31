package com.book.projectps.service;


import com.book.projectps.model.Commission;

import java.util.List;

public interface CommissionService {
    List<Commission> findAll();
    Commission findById(Long id);
    Commission save(Commission commission);
    void deleteById(Long id);
}
