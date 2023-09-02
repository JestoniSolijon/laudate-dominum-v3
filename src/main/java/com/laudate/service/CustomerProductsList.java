package com.laudate.service;

import com.laudate.entity.Products;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerProductsList {

    private ProductsService productsService;

    public CustomerProductsList(ProductsService productsService) {
        this.productsService = productsService;
    }

    public List<Products> productsList(Map<Integer, Integer> customerCart) {

        // Initialize a map collection to store the session data
        Map<Integer, Integer> orders = new HashMap<>();

        // If session data is not null, add it to the orders map
        if ((Map<Integer, Integer>) customerCart != null) {
            orders.putAll((Map<Integer, Integer>) customerCart);
        }

        List<Products> productsList = new ArrayList<>(); // Create a list to store Products objects

        // Now you can iterate over the orders map and populate the productsList
        for (Map.Entry<Integer, Integer> entry : orders.entrySet()) {
            Integer productId = entry.getKey();
            Integer quantity = entry.getValue();

            // Do something with productId and quantity
            Optional<Products> product = productsService.getProductById(productId);

            if (product.isPresent()) {
                Products products = new Products();
                products.setProductName(product.get().getProductName());
                products.setProductId(product.get().getProductId());
                products.setPrice(product.get().getPrice());
                products.setQuantity(quantity);

                productsList.add(products); // Add the products object to the list
            }
        }

        return productsList;
    }
}
