package com.laudate.dominum.controller;

import com.laudate.dominum.entity.Customer;
import com.laudate.dominum.entity.Orders;
import com.laudate.dominum.entity.Products;
import com.laudate.dominum.service.CustomerProductsList;
import com.laudate.dominum.service.LocationService;
import com.laudate.dominum.service.ProductsService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
@SessionAttributes({"customerSession", "productId", "quantity"})
public class CheckoutController {

    private ProductsService productsService;
    private LocationService locationService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CustomerProductsList customerProductsList;



    public CheckoutController(ProductsService productsService, LocationService locationService) {
        this.productsService = productsService;
        this.locationService = locationService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/checkout")
    public String checkout(Model model) throws IOException {

        model.addAttribute("customerProducts", httpSession.getAttribute("customerProducts"));
        model.addAttribute("totalPrice", httpSession.getAttribute("totalPrice"));
        model.addAttribute("customer", new Customer());

        // Get products added to cart
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        model.addAttribute("productsCount", customerProductsList.productsList(sessionData).size());

        /*if (httpSession.getAttribute("customerProducts") == null) {
            return "redirect:/";
        }
*/
        // Get all the regions
        model.addAttribute("regions", locationService.getRegion());

        return "checkout";
    }

    @PostMapping("/checkout")
    public String saveCustomer(
            @Valid @ModelAttribute("customer")Customer customer,
            BindingResult bindingResult,
            @RequestParam(value = "productId", required = false) Integer[] productId,
            @RequestParam(value = "quantity", required = false) Integer[] quantity,
            Model model,
            HttpSession session) throws IOException {

        model.addAttribute("regions", locationService.getRegion());

        if (bindingResult.hasErrors())
            return "/checkout";

        Optional<Products> products;
        Map<Optional<Products>, Integer> customerProducts = new HashMap<>();
        Integer totalPrice = 0;

        for (int i = 0; i < productId.length; i++) {
            try {
                products = productsService.getProductById(productId[i]);
                customerProducts.put(products, quantity[i]);
                totalPrice += products.get().getPrice() * quantity[i];
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

        httpSession.setAttribute("customerProducts", customerProducts);
        httpSession.setAttribute("totalPrice", totalPrice);

        model.addAttribute("customerSession", customer);
        session.setAttribute("checkoutApproved", true);

        return "redirect:/payment";
    }


    @PostMapping("/cart-checkout")
    public String saveCustomerCart(@RequestParam(value = "productId", required = false) Integer[] productId,
                                   @RequestParam(value = "quantity", required = false) Integer[] quantity) {

        Optional<Products> products;
        Map<Optional<Products>, Integer> customerProducts = new HashMap<>();
        Integer totalPrice = 0;
        for (int i = 0; i < productId.length; i++) {
            try {
                products = productsService.getProductById(productId[i]);
                customerProducts.put(products, quantity[i]);
                totalPrice += products.get().getPrice() * quantity[i];
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

        httpSession.setAttribute("customerProducts", customerProducts);
        httpSession.setAttribute("totalPrice", totalPrice);

        return "redirect:/checkout";
    }

}
