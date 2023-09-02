package com.laudate.dominum.controller;

import com.laudate.dominum.entity.Products;
import com.laudate.dominum.service.CustomerProductsList;
import com.laudate.dominum.service.ProductsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductController {

    private final HttpSession httpSession;
    private final ProductsService productsService;

    private final CustomerProductsList customerProductsList;

    public ProductController(HttpSession httpSession, ProductsService productsService, CustomerProductsList customerProductsList) {
        this.httpSession = httpSession;
        this.productsService = productsService;
        this.customerProductsList = customerProductsList;
    }

    @GetMapping("product")
    public String getProduct(@RequestParam("productId") Integer id, Model model) {

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        model.addAttribute("productsCount", customerProductsList.productsList(sessionData).size());

        Optional<Products> products = productsService.getProductById(id);
        model.addAttribute("products", products);

        return "product";
    }

}
