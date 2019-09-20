package com.example.springhomeworktasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("Start")
public class ShopPackageStart implements ShowProducts{
    private ShopCartService shopCartService;

    @Autowired
    public ShopPackageStart(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void showProducts(){
        int summaryProductPrice = 0;
        for (Product product : shopCartService.getProductCollection()){
            int priceProductItem = product.getProductPrice().intValue();
            summaryProductPrice += priceProductItem;
        }
        shopCartService.getProductCollection().forEach(System.out::println);
        System.out.println("Summary shop cart of Start profile " + new BigDecimal(summaryProductPrice));

    }
}
