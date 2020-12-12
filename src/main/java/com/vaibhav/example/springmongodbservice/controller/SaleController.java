package com.vaibhav.example.springmongodbservice.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaibhav.example.springmongodbservice.document.Item;
import com.vaibhav.example.springmongodbservice.document.Sale;
import com.vaibhav.example.springmongodbservice.document.Tag;
import com.vaibhav.example.springmongodbservice.model.StoreStatistics;
import com.vaibhav.example.springmongodbservice.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    ResponseEntity<List<Sale>> findSalesByParameter(@RequestParam("purchaseMethod") String purchaseMethod,
                                                    @RequestParam("coupon") boolean isCouponUsed,
                                                    @RequestParam("storeLocation") String storeLocation) {
        List<Sale> sales = saleRepository.findAllSalesByParameters(purchaseMethod, isCouponUsed, storeLocation);

        return ResponseEntity.ok().body(sales);
    }

    //store sales stats
    //total sales
    //most bought items
    //most bought tags
    //how many used coupons
    //
    @GetMapping("/storestatistics")
    public ResponseEntity<StoreStatistics> storeStatisticsByLocation(@RequestParam("storeLocation") String storeLocation){
        List<Sale> sales = saleRepository.findAllByStoreLocation(storeLocation);

        List<Item> items = sales.stream().map(e -> e.getItems())
                .collect(Collectors.toList())
                .stream().flatMap( x -> x.stream())
                .collect(Collectors.toList());

        //mostBoughtItemName
        Map<String, Long> itemsFrequencies = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.counting()));

        String mostBoughtItemName = Collections.max(itemsFrequencies.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();

        //totalSales
        BigDecimal totalSales = items.stream().map( e -> e.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //mostBoughtCategory

        List<Tag> tags = items.stream().map(item -> item.getTags())
                .collect(Collectors.toList())
                .stream().flatMap( x -> x.stream())
                .collect(Collectors.toList())
                .stream().distinct().collect(Collectors.toList());

        Map<String, Long> tagCounts = tags.stream().collect(Collectors.groupingBy(Tag::getTagName, Collectors.counting()));
        String mostBoughtCategory = Collections.max(tagCounts.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();

        StoreStatistics storeStatistics = StoreStatistics.builder()
                .storeName(storeLocation)
                .mostBoughtCategory(mostBoughtCategory)
                .mostBoughtItemName(mostBoughtItemName)
                .totalSales(totalSales)
                .build();

        return ResponseEntity.ok().body(storeStatistics);

    }



}
