package com.laudate.dominum.controller;

import com.laudate.dominum.entity.Customer;
import com.laudate.dominum.entity.Orders;
import com.laudate.dominum.entity.Products;
import com.laudate.dominum.service.CustomerProductsList;
import com.laudate.dominum.service.CustomerService;
import com.laudate.dominum.service.LocationService;
import com.laudate.dominum.service.ProductsService;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@SessionAttributes({"customerSession", "productId", "quantity"})
public class PaymentController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CustomerService customerService;

    private HttpSession httpSession;

    private CustomerProductsList customerProductsList;

    public PaymentController(HttpSession httpSession, CustomerProductsList customerProductsList) {
        this.httpSession = httpSession;
        this.customerProductsList = customerProductsList;
    }

    @GetMapping("/payment")
    public String payment(Model model, HttpSession session) throws IOException {

        model.addAttribute("regions", locationService.getRegion());
        if (session.getAttribute("checkoutApproved") == null) return "access-denied";

        model.addAttribute("customerProducts", httpSession.getAttribute("customerProducts"));
        model.addAttribute("totalPrice", httpSession.getAttribute("totalPrice"));

        model.addAttribute("customer", new Customer());

        // Get products added to cart
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        model.addAttribute("productsCount", customerProductsList.productsList(sessionData).size());


        if (httpSession.getAttribute("customerProducts") == null) { return "redirect:/"; }

        return "payment";
    }

    @PostMapping("/payment")
    public String savePayment(@RequestParam("productId") Integer[] productIds,
                              @RequestParam("quantity") Integer[] quantities, Model model) {

        Customer customer = (Customer) model.getAttribute("customerSession");

        for (int i = 0; i < productIds.length; i++) {
            Optional<Products> product = productsService.getProductById(productIds[i]);

            if (product.isPresent()) {
                Orders orders = new Orders();
                orders.setCustomer(customer);
                orders.setQuantity(quantities[i]);
                orders.setOrderDate(new Date());
                orders.getProducts().add(product.get());

                customer.add(orders);
            }
        }

        httpSession.invalidate();

        customerService.saveCustomer(customer);

        return "order-success";
    }

    @GetMapping("success")
    public String test(HttpSession session) {

        if (session.getAttribute("checkoutApproved") == null) return "redirect:/";

        return "order-success";
    }
}
