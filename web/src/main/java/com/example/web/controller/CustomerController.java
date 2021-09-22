package com.example.web.controller;

import com.example.web.dto.request.CustomerRequest;
import com.example.web.dto.response.CustomerResponse;
import com.example.web.exception.RequestParamInvalidException;
import com.example.web.service.CustomerService;
import com.example.web.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
        ModelAndView modelAndView = new ModelAndView("customer-detail");
        modelAndView.addObject("customer", service.getById(httpServletRequest, id));
        return modelAndView;
    }

    @GetMapping({"/add"})
    public ModelAndView showFormAdd(HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView("customer-add");
        modelAndView.addObject("customer", new CustomerRequest());
        return modelAndView;
    }

    @PostMapping({"/add"})
    public ModelAndView addCustomer(HttpServletRequest httpServletRequest, @ModelAttribute("customer") CustomerRequest request){
        Optional<CustomerResponse> response = service.add(httpServletRequest, request);
        if (response.isEmpty()) {
            return new ModelAndView("redirect:/error");
        }
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormEdit(HttpServletRequest httpServletRequest, @PathVariable String id){
        Optional<CustomerResponse> customerResponse = service.getById(httpServletRequest, id);

        if(customerResponse.isEmpty()){
            return new ModelAndView("redirect:/error");
        } else {
            ModelAndView modelAndView = new ModelAndView("customer-update");
            modelAndView.addObject("customer", customerResponse.get());
            return modelAndView;
        }
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateCustomer(HttpServletRequest httpServletRequest, @PathVariable String id){
        CustomerRequest customerRequest = new CustomerRequest(httpServletRequest.getParameter("customerName"),
                httpServletRequest.getParameter("phone"));
        Optional<CustomerResponse> response = service.updateById(httpServletRequest, id, customerRequest);

        if (response.isEmpty()) {
            return new ModelAndView("redirect:/error");
        }
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(HttpServletRequest httpServletRequest, @PathVariable String id){
        Optional<CustomerResponse> response = service.deleteById(httpServletRequest, id);
        if (response.isEmpty()) {
            return new ModelAndView("redirect:/error");
        }
        return new ModelAndView("redirect:/customers");
    }
}
