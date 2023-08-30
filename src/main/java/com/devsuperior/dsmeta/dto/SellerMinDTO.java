package com.devsuperior.dsmeta.dto;

public class SellerMinDTO {
    
    private String name;
    private Double total;

    public SellerMinDTO() {
    }

    public SellerMinDTO(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }

}
