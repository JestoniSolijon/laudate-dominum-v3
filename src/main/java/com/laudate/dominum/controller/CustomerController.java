package com.laudate.dominum.controller;

import com.laudate.dominum.entity.Products;
import com.laudate.dominum.service.CustomerProductsList;
import com.laudate.dominum.service.CustomerService;
import com.laudate.dominum.service.LocationService;
import com.laudate.dominum.service.ProductsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"customerSession", "productId", "quantity"})
public class CustomerController {

    private final CustomerService customerService;
    private final ProductsService productsService;
    private final LocationService locationService;

    private final HttpSession httpSession;

    private final CustomerProductsList customerProductsList;

    @Autowired
    private HttpSession session;

    public CustomerController(CustomerService customerService, ProductsService productsService, LocationService locationService, HttpSession httpSession, CustomerProductsList customerProductsList) {
        this.customerService = customerService;
        this.productsService = productsService;
        this.locationService = locationService;
        this.httpSession = httpSession;
        this.customerProductsList = customerProductsList;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String getHome(Model model) {

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        model.addAttribute("productsCount", customerProductsList.productsList(sessionData).size());

        model.addAttribute("productsList", productsService.getProductList());
        model.addAttribute("products", new Products());

        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        model.addAttribute("productsCount", customerProductsList.productsList(sessionData).size());

        return "about";
    }

}
