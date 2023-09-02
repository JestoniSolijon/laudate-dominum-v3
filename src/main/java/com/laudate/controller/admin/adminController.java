package com.laudate.controller.admin;

import com.laudate.dominum.entity.*;
import com.laudate.dominum.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class adminController {

    private ProductsService productsService;

    private OrdersServices ordersServices;

    private InquiryService inquiryService;

    private CustomerService customerService;

    private SmsService smsService;
    public adminController(ProductsService productsService, OrdersServices ordersServices, InquiryService inquiryService, CustomerService customerService, SmsService smsService) {
        this.productsService = productsService;
        this.ordersServices = ordersServices;
        this.inquiryService = inquiryService;
        this.customerService = customerService;
        this.smsService = smsService;
    }

    @GetMapping("/admin/dashboard")
    public String showProductList(Model model) {

        model.addAttribute("products", productsService.getProductList());
        model.addAttribute("productsCount", productsService.getProductList().size());
        model.addAttribute("productsObject", new Products());

        model.addAttribute("orders", ordersServices.getAllOrders());
        model.addAttribute("ordersCount", ordersServices.getAllOrders().size());
        model.addAttribute("ordersObject", new Orders());

        model.addAttribute("inquiries", inquiryService.getAllInquiry());
        model.addAttribute("inquiryCount", inquiryService.getAllInquiry().size());
        model.addAttribute("inquiryObject", new Inquiry());

        model.addAttribute("sms", smsService.findAll());
        model.addAttribute("smsCount", smsService.findAll().size());
        model.addAttribute("smsObject", new Sms());

        return "/admin/dashboard";
    }
}
