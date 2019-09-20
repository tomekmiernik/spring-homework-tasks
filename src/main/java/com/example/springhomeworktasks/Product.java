package com.example.springhomeworktasks;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Product {
    private String productName;
    private BigDecimal productPrice;


    public Product(String productName, BigDecimal productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    void setProductName(String productName) {
        this.productName = productName;
    }

    BigDecimal getProductPrice() {
        return productPrice;
    }

    void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice = " + productPrice +
                '}';
    }
}
