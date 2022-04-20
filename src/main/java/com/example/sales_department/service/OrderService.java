package com.example.sales_department.service;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Order;
import com.example.sales_department.repository.ContractRepository;
import com.example.sales_department.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void add(Order order){
        orderRepository.saveAndFlush(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }
}
