package com.vaibhav.example.springmongodbservice.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaibhav.example.springmongodbservice.document.Sale;
import com.vaibhav.example.springmongodbservice.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping(path = "/allsales")
    public ResponseEntity<List<Sale>> getAllSale(){
        List<Sale> sales = saleRepository.findAll();
        return ResponseEntity.ok().body(sales);
    }

    @GetMapping(path = "/totalsales")
    public ResponseEntity<Integer> countSales(){
        int count = saleRepository.findAll().size();
        return ResponseEntity.ok().body(count);
    }

    @GetMapping(path = "/salesby")
    ResponseEntity<List<Sale>> findSalesByParameter(@RequestParam("purchaseMethod") String purchaseMethod){
        List<Sale> sales = saleRepository.findAllByPurchaseMethod(purchaseMethod);
        return ResponseEntity.ok().body(sales);
    }



}
