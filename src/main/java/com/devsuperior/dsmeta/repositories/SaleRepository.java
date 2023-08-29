package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s " +
           "INNER JOIN s.seller se " +
           "WHERE s.date BETWEEN :minDate AND :maxDate " +
           "AND (:name IS NULL OR UPPER(se.name) LIKE CONCAT('%', UPPER(:name), '%')) " +
           "ORDER BY s.date DESC")
    Page<Sale> search1(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
}