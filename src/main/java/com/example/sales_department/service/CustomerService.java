package com.example.sales_department.service;

import com.example.sales_department.entity.Customer;
import com.example.sales_department.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void add(Customer customer){
        customerRepository.saveAndFlush(customer);
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}

