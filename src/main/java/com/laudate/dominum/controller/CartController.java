package com.laudate.dominum.controller;

import com.laudate.dominum.entity.Products;
import com.laudate.dominum.service.CustomerProductsList;
import com.laudate.dominum.service.ProductsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;

@Controller
public class CartController {

    private ProductsService productsService;

    private HttpSession httpSession;

    private CustomerProductsList customerProductsList;

    public CartController(HttpSession httpSession, ProductsService productsService, CustomerProductsList customerProductsList) {
        this.httpSession = httpSession;
        this.productsService = productsService;
        this.customerProductsList = customerProductsList;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        List<Products> productsList = customerProductsList.productsList(sessionData);

        model.addAttribute("productsList", productsList);
        model.addAttribute("productsCount", productsList.size());

        int total = 0;
        for (Products list : productsList) {
            total += list.getPrice() * list.getQuantity();
        }

        model.addAttribute("productsTotal", total);

        return "cart";
    }


    @PostMapping("/cart")
    public String saveCart(@RequestParam("quantity") int quantity,
                           @RequestParam("productId") int productId) {

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> orders = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");

        // If customerCart doesn't exist in the session, create a new map
        if (orders == null) {
            orders = new HashMap<>();
        }

        // Add the new item or update the quantity if the product already exists
        orders.put(productId, quantity);

        // Set the updated customerCart back into the session
        httpSession.setAttribute("customerCart", orders);

        return "redirect:/product?productId=" + productId;
    }

    @PostMapping("/cart/update-quantity")
    public String updateQuantity(@RequestParam("quantity") int quantity,
                                 @RequestParam("productId") int productId) {

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        if (sessionData != null && sessionData.containsKey(productId)) {
            sessionData.put(productId, quantity); // Update the value associated with the productId key
        }

        httpSession.setAttribute("customerCart", sessionData); // Set the updated map back to the session

        return "redirect:/cart";
    }

    @PostMapping("/cart/remove-product")
    public String removeProduct(@RequestParam("productId") int productId) {

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        if (sessionData != null) {
            sessionData.remove(productId);
        }

        httpSession.setAttribute("customerCart", sessionData); // Set the updated map back to the session

        return "redirect:/cart";
    }
}
