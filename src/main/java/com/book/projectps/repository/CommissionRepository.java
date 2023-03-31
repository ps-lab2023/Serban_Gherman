package com.book.projectps.repository;

import com.book.projectps.model.Commission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends CrudRepository<Commission, Long> {
    Iterable<Commission> findByClientId(Long clientId);
}
