package com.example.example130921.controller;

import com.example.example130921.dao.entity.Customer;
import com.example.example130921.dto.request.CustomerRequest;
import com.example.example130921.dto.response.CustomerResponse;
import com.example.example130921.service.CustomerService;
import com.example.example130921.service.impl.CustomerServiceImpl;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController extends AbstractController<CustomerService>{

    @GetMapping("")
    public ResponseEntity<?> getAllCustomers(){
        return response(service.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return response(service.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody CustomerRequest customerRequest){
        return response(service.add(customerRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id, @RequestBody CustomerRequest customerRequest){
        return response(service.updateById(id, customerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        return response(service.deleteById(id));
    }
}
