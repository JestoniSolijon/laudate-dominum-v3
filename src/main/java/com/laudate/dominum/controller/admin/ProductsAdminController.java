package com.laudate.dominum.controller.admin;

import com.laudate.dominum.entity.Products;
import com.laudate.dominum.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ProductsAdminController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/remove-product")
    public String removeProduct(@RequestParam("productId") int productId,
                                Model model) {

        productsService.deleteProductById(productId);

        model.addAttribute("active-menu", "products");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute("products")Products products) {

        productsService.saveProduct(products);
        return "redirect:/admin/dashboard";
    }


}
