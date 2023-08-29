package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_sales INNER JOIN tb_sellers ON tb_sales.seller_id = tb_sellers.id WHERE tb_sales.date BETWEEN :minDate AND :maxDate AND (:name IS NULL OR UPPER(tb_sellers.name) LIKE UPPER(:name)) ORDER BY tb_sales.date DESC")
    List<Sale> search1(LocalDate minDate, LocalDate maxDate, String name);
}
