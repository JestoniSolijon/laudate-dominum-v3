package com.laudate.dominum.controller.admin;

import com.laudate.dominum.entity.Customer;
import com.laudate.dominum.entity.Orders;
import com.laudate.dominum.service.CustomerService;
import com.laudate.dominum.service.OrdersServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class OrdersAdminController {

    private OrdersServices ordersServices;

    private CustomerService customerService;
    public OrdersAdminController(OrdersServices ordersServices, CustomerService customerService) {

        this.ordersServices = ordersServices;
        this.customerService = customerService;
    }

    @PostMapping("/approve-order")
    public String approveOrder(@RequestParam("orderId") int orderId,
                               @RequestParam("customerId") int customerId) {

        // Fetch the order using Optional
        Optional<Orders> ordersOptional = ordersServices.findOrderById(orderId);

        if (ordersOptional.isPresent()) {
            Orders orders = ordersOptional.get();

            // Fetch the customer
            Customer customer = customerService.findCustomerById(customerId).orElse(null);

            if (customer != null) {
                // Assign the customer to the order
                orders.setCustomer(customer);

                // Set the order status to 1
                orders.setOrderStatus(1);

                // Save the updated order
                ordersServices.approveOrder(orders);
            }
        }

        return "redirect:/admin/dashboard";
    }

    @PostMapping("/cancel-order")
    public String cancelOrder(@RequestParam("orderId") int orderId,
                               @RequestParam("customerId") int customerId) {

        // Fetch the order using Optional
        Optional<Orders> ordersOptional = ordersServices.findOrderById(orderId);

        if (ordersOptional.isPresent()) {
            Orders orders = ordersOptional.get();

            // Fetch the customer
            Customer customer = customerService.findCustomerById(customerId).orElse(null);

            if (customer != null) {
                // Assign the customer to the order
                orders.setCustomer(customer);

                // Set the order status to 1
                orders.setOrderStatus(2);

                // Save the updated order
                ordersServices.approveOrder(orders);
            }
        }

        return "redirect:/admin/dashboard";
    }
}
