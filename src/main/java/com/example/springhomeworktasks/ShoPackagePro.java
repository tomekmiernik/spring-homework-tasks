package com.example.springhomeworktasks;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("Pro")
@ConfigurationProperties("pro")
public class ShoPackagePro implements ShowProducts {

    private ShopCartService shopCartService;

    private BigDecimal discountValue;
    private BigDecimal taxValue;

    public ShoPackagePro(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void showProducts() {
        double summaryProductPrice = 0.0;
        for (Product product : shopCartService.getProductCollection()) {
            product.setProductPrice(product.getProductPrice()
                    .subtract(discountValue.multiply(new BigDecimal(100)))
                    .multiply(taxValue).setScale(2,BigDecimal.ROUND_HALF_UP));
            summaryProductPrice += product.getProductPrice().doubleValue();
        }
        shopCartService.getProductCollection().forEach(System.out::println);
        System.out.println("Summary shop cart of Pro profile " + new BigDecimal(summaryProductPrice)
                .setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }
}
