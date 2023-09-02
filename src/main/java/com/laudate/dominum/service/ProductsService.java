package com.laudate.dominum.service;

import com.laudate.dominum.entity.Products;
import com.laudate.dominum.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> getProductList() {
        return productsRepository.findAll();
    }

    public Optional<Products> getProductById(Integer id) { return productsRepository.findById(id); }

    public void deleteProductById(Integer id) { productsRepository.deleteById(id); }

    public void saveProduct(Products products) { productsRepository.save(products); }
}
