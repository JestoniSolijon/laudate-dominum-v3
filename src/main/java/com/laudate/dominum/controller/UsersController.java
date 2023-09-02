package com.laudate.dominum.controller;

import com.laudate.dominum.entity.Customer;
import com.laudate.dominum.entity.Users;
import com.laudate.dominum.service.UsersService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {

        model.addAttribute("users", new Users());

        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(@Valid @ModelAttribute("users") Users users,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors())  return "/signup";

        try {
            usersService.save(users);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("userExist", true);

            return "/signup";
        }

        return "home";
    }

}
