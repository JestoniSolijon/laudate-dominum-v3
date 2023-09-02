package com.laudate.dominum.controller;

import com.laudate.dominum.entity.Inquiry;
import com.laudate.dominum.service.CustomerProductsList;
import com.laudate.dominum.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class ContactController {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CustomerProductsList customerProductsList;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/contact")
    public String contact(Model model) {

        // Get products added to cart
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> sessionData = (Map<Integer, Integer>) httpSession.getAttribute("customerCart");
        model.addAttribute("productsCount", customerProductsList.productsList(sessionData).size());

        model.addAttribute("inquiry", new Inquiry());
        return "contact";
    }

    @PostMapping("/contact")
    private String saveInquiry(
            @Valid @ModelAttribute("inquiry") Inquiry inquiry,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())  return "/contact";

        inquiry.setInquiryDate(new Date());
        inquiryService.save(inquiry);

        return "modal/send-success";
    }

}
