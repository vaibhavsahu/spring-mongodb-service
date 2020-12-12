package com.vaibhav.example.springmongodbservice.model;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreStatistics {
    private String storeName;
    private BigDecimal totalSales;
    private String mostBoughtItemName;
    private String mostBoughtCategory;



}
