package com.example.springhomeworktasks;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("Plus")
@ConfigurationProperties("plus")
public class ShopPackagePlus implements ShowProducts{
    private ShopCartService shopCartService;

    public ShopPackagePlus(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }
    private BigDecimal taxValue;

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void showProducts(){
        double summaryProductPrice = 0.0;
        for (Product product: shopCartService.getProductCollection()) {
            product.setProductPrice(product.getProductPrice().multiply(taxValue));
            summaryProductPrice += product.getProductPrice().doubleValue();
        }
        shopCartService.getProductCollection().forEach(System.out::println);
        System.out.println("Summary shop cart of Plus profile " + new BigDecimal(summaryProductPrice)
                .setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }
}
