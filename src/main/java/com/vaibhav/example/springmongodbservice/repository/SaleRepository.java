package com.vaibhav.example.springmongodbservice.repository;

import com.vaibhav.example.springmongodbservice.document.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
    @Query("{ 'purchaseMethod' : ?0, 'couponUsed': ?1, 'storeLocation': ?2 }")
    List<Sale> findAllSalesByParameters(String method, boolean isCouponUsed, String purchaseMethod);

    List<Sale> findAllByStoreLocation(String storeLocation);
}
