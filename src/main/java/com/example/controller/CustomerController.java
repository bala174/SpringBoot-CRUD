package com.example.controller;

import com.example.model.Customer;
import com.example.response.CommonResponse;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> addCustomer(@RequestBody Customer customer) {
        CommonResponse response = new CommonResponse();
        try{
            response = customerService.addCustomer(customer);
        }catch(Exception e){
            response.setCode(500);
            response.setStatus("Failed");
            response.setData(e.getMessage());
            response.setErrorMessage("Some thing goes wrong with your code");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteCustomer(@PathVariable long id) {
        CommonResponse response = new CommonResponse();
        try{
            response = customerService.deleteCustomer(id);
        }catch(Exception e){
            response.setCode(500);
            response.setStatus("Failed");
            response.setData(e.getMessage());
            response.setErrorMessage("Some thing goes wrong with your code");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<CommonResponse> updateCustomer(@RequestBody Customer cust) {
        CommonResponse response = new CommonResponse();
        try{
            response = customerService.updateCustomer(cust);
        }catch(Exception e){
            response.setCode(500);
            response.setStatus("Failed");
            response.setData(e.getMessage());
            response.setErrorMessage("Some thing goes wrong with your code");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getcustomer")
    public ResponseEntity<CommonResponse> getCustomer() {
        CommonResponse response = new CommonResponse();
        try{
            response = customerService.getCustomer();
        }catch(Exception e){
            response.setCode(500);
            response.setStatus("Failed");
            response.setData(e.getMessage());
            response.setErrorMessage("Some thing goes wrong with your code");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getCustomer/id/{id}")
    public ResponseEntity<CommonResponse> getCustomerById(@PathVariable long id) {
        CommonResponse response = new CommonResponse();
        try{
            response = customerService.getCustomerById(id);
        }catch(Exception e){
            response.setCode(500);
            response.setStatus("Failed");
            response.setData(e.getMessage());
            response.setErrorMessage("Some thing goes wrong with your code");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getCustomer/city/{city}")
    public ResponseEntity<CommonResponse> getCustomerByCity(@PathVariable String city) {
        CommonResponse response = new CommonResponse();
        try{
            response = customerService.getCustomerByCity(city);
        }catch(Exception e){
            response.setCode(500);
            response.setStatus("Failed");
            response.setData(e.getMessage());
            response.setErrorMessage("Some thing goes wrong with your code");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/search")
    public ResponseEntity<CommonResponse> getFilterCustomer(@RequestBody Customer customer){
        CommonResponse response = new CommonResponse();
        try{
            response = customerService.getFilterCustomer(customer.getName(),customer.getCity());
        }catch(Exception e){
            response.setCode(500);
            response.setStatus("Failed");
            response.setData(e.getMessage());
            response.setErrorMessage("Some thing goes wrong with your code");
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/getCustomers")
    public List<Customer> getCustomers(Pageable pageable){
        return customerService.getCustomers(pageable);
    }
}
