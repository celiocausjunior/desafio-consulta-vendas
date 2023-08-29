package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> search1(String startDate, String endDate, String name, Pageable pageable) {

		LocalDate start = starDate(startDate);
		LocalDate end = endDate(endDate);

		Page<Sale> result = repository.search1(start, end, name, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}

	public LocalDate starDate(String startDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if (startDate == null || startDate.equals("")) {
			return today.minusYears(1L);
		} else {
			return LocalDate.parse(startDate);
		}
	}

	public LocalDate endDate(String endDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		if (endDate == null || endDate.equals("")) {
			return today;
		} else {
			return LocalDate.parse(endDate);
		}
	}
}
