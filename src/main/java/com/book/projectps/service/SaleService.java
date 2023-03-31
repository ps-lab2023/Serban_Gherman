package com.book.projectps.service;


import com.book.projectps.model.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> findAll();
    Sale findById(Long id);
    Sale save(Sale sale);
    void deleteById(Long id);
}