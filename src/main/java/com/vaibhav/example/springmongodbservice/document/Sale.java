package com.vaibhav.example.springmongodbservice.document;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.ws.BindingType;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "sales")
public class Sale {

    @Id
    private String id;

    private Date saleDate;

    private String storeLocation;

    private Customer customer;

    private List<Item> items;

    private boolean couponUsed;

    private String purchaseMethod;
}
