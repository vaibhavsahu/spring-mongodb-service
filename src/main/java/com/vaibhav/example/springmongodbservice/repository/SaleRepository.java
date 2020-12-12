package com.vaibhav.example.springmongodbservice.repository;

import com.vaibhav.example.springmongodbservice.document.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
    @Query("{ 'purchaseMethod' : ?0 }")
    List<Sale> findAllByPurchaseMethod(String purchaseMethod);
}
