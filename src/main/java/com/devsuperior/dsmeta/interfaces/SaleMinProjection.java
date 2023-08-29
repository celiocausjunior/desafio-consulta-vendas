package com.devsuperior.dsmeta.interfaces;

import java.time.LocalDate;

public interface SaleMinProjection {
    Long getId();
	Double getAmount();
	LocalDate getDate();
    String getName();
}
