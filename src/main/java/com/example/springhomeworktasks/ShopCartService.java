package com.example.springhomeworktasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
public class ShopCartService {

    private Collection<Product> productCollection;

    @Autowired
    public ShopCartService() {
        this.productCollection = new ArrayList<>();
        createProductCollection();
    }

    private void createProductCollection() {
        for (int productIteration = 0; productIteration < 5; productIteration++) {
            Product product = setDefaultNameOfProduct(productIteration);
            setDefaultPriceOfProduct(product);
            productCollection.add(product);
        }
    }

    Collection<Product> getProductCollection() {
        return productCollection;
    }

    private void setDefaultPriceOfProduct(Product product) {
        product.setProductPrice(new BigDecimal(new Random().nextInt(250) + 50));
    }

    private Product setDefaultNameOfProduct(int productIteration) {
        Product product = new Product();
        product.setProductName("Product " + (productIteration + 1));
        return product;
    }
}
