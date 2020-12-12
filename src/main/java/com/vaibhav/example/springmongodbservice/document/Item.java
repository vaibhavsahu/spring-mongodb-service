package com.vaibhav.example.springmongodbservice.document;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private String name;
    private List<Tag> tags;
    private BigDecimal price;
    private Long quantity;
}
