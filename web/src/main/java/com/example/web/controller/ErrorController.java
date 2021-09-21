package com.example.web.controller;

import com.example.web.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractController<CustomerService> {
    @GetMapping(value = "")
    public ModelAndView getUsers(HttpServletRequest httpServletRequest) {
        return new ModelAndView("error-page");
    }
}
