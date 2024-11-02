package com.example.service;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import com.example.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CommonResponse addCustomer(Customer cust) {
        CommonResponse response = new CommonResponse();
        if(customerRepository.existsById(cust.getId())){
            response.setCode(409);
            response.setStatus("Failed");
            response.setData(cust);
            response.setErrorMessage("Customer Already exist");
        }else{
            customerRepository.save(cust);
            response.setCode(200);
            response.setStatus("Success");
            response.setData(cust);
            response.setSuccessMessage("Data inserted successfully");
        }
        return response;
    }

    public CommonResponse deleteCustomer(long cid) {
        CommonResponse response = new CommonResponse();
        if(customerRepository.existsById(cid)){
            customerRepository.deleteById(cid);
            response.setCode(200);
            response.setStatus("success");
            response.setSuccessMessage("Customer with "+cid+" is deleted successfully");
        }else{
            response.setCode(409);
            response.setStatus("Failed");
            response.setErrorMessage("There is no customer with this "+cid+" customer id");
        }
        return response;
    }

    public CommonResponse updateCustomer(Customer cust) {
        CommonResponse response = new CommonResponse();
        if(customerRepository.existsById(cust.getId())){
            customerRepository.save(cust);
            response.setCode(200);
            response.setStatus("Success");
            response.setData(cust);
            response.setSuccessMessage("customer Data updated successfully");
        }else{
            response.setCode(409);
            response.setStatus("Failed");
            response.setData(cust);
            response.setErrorMessage("There is no customer to Update the value");
        }
        return response;
    }

    public CommonResponse getCustomer() {
        CommonResponse response = new CommonResponse();
        List<Customer> cust = new ArrayList<>(customerRepository.findAll());
        response.setCode(200);
        response.setStatus("Success");
        response.setSuccessMessage("All the customer retrieved successfully");
        response.setData(cust);
        return response;
    }

    public CommonResponse getCustomerById(long id) {
        CommonResponse response = new CommonResponse();
        if(customerRepository.existsById(id)){
            response.setData(customerRepository.findById(id).get());
            response.setCode(200);
            response.setStatus("Success");
            response.setSuccessMessage("customer retrieved successfully");
        }
        else{
            response.setCode(409);
            response.setStatus("Failed");
            response.setErrorMessage("There is no customer with this ID");
        }
        return response;
    }

    public CommonResponse getCustomerByCity(String city) {
        CommonResponse response = new CommonResponse();
        List<Customer> cust = customerRepository.findByCity(city);
        if(cust.size() == 0){
            response.setCode(409);
            response.setStatus("Failed");
            response.setErrorMessage("There is no customer in "+city);
        }else{
            response.setData(cust);
            response.setCode(200);
            response.setStatus("Success");
            response.setSuccessMessage("customer retrieved successfully");
        }
        return response;
    }
    public CommonResponse getFilterCustomer(String name,String city) {
        CommonResponse response = new CommonResponse();
        List<Customer> cust = customerRepository.findByNameAndCity(name,city);
        if(cust.size() == 0){
            response.setCode(409);
            response.setStatus("Failed");
            response.setErrorMessage("There is no customer has name like "+name+"in "+city);
        }else{
            response.setData(cust);
            response.setCode(200);
            response.setStatus("Success");
            response.setSuccessMessage("customer retrieved successfully");
        }
        return response;
    }
    public List<Customer> getCustomers(Pageable pageable){
        Page<Customer> page = customerRepository.findAll(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSortOr(Sort.by(Sort.Direction.DESC, "name")))
        );
        return page.getContent();
    }
}
