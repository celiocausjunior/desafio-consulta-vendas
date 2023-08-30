package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s " +
           "INNER JOIN s.seller se " +
           "WHERE s.date BETWEEN :minDate AND :maxDate " +
           "AND (:name IS NULL OR UPPER(se.name) LIKE CONCAT('%', UPPER(:name), '%')) " +
           "ORDER BY s.date DESC")
    Page<Sale> search1(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query( "SELECT new com.devsuperior.dsmeta.dto.SellerMinDTO (obj.seller.name, SUM(obj.amount)) " +
    "FROM Sale obj " +
    "INNER JOIN obj.seller s " +
    "WHERE obj.date BETWEEN :startDate AND :endDate " +
    "AND (:name IS NULL OR UPPER(s.name) LIKE CONCAT('%', UPPER(:name), '%')) " +
    "GROUP BY s.name")
    Page<SellerMinDTO> search2(LocalDate startDate, LocalDate endDate, String name, Pageable pageable);
}