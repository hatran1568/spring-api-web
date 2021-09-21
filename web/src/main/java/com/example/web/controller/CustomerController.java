package com.example.web.controller;

import com.example.web.service.CustomerService;
import com.example.web.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customers")
public class CustomerController extends AbstractController<CustomerService>{
    @GetMapping("")
    public ModelAndView getCustomers(HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView("customer");
        modelAndView.addObject("customerList", service.getCustomers(httpServletRequest).get());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getCustomerDetail(HttpServletRequest httpServletRequest, @PathVariable String id){
        if (!StringUtils.isNumeric(id)){
            return new ModelAndView("error-page");
        } else {
            ModelAndView modelAndView = new ModelAndView("customer-detail");
            modelAndView.addObject("customer", service.getById(httpServletRequest, Integer.parseInt(id)).get());
            return modelAndView;
        }

    }
}
