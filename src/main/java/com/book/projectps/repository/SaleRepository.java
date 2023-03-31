package com.book.projectps.repository;

import com.book.projectps.model.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {
    Iterable<Sale> findByIsAvailable(boolean isAvailable);
}
