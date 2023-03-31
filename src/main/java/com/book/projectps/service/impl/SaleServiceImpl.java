package com.book.projectps.service.impl;


import com.book.projectps.model.Sale;
import com.book.projectps.repository.SaleRepository;
import com.book.projectps.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> findAll() {
        return (List<Sale>) saleRepository.findAll();
    }

    @Override
    public Sale findById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }
}
