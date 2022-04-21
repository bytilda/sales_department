package com.example.sales_department.service;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Order;
import com.example.sales_department.repository.ContractRepository;
import com.example.sales_department.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
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

    public List<Order> findByAll(BigInteger inn, Long specification, LocalDate startShipment, LocalDate endShipment, Long contractNumber){
        List<Order> orders = orderRepository.findAll();

        for (int i = 0; i < orders.size(); ){
            if (inn != null){
                if(!orders.get(i).getIdSpecification().getIdContract().getIdCustomer().getInn().equals(inn)){
                    orders.remove(i);
                    continue;
                }
            }
            if (specification != null){
                if (!orders.get(i).getIdSpecification().getApplicationNumber().equals(specification)){
                    orders.remove(i);
                    continue;
                }
            }
            if (startShipment != null){
                if (!orders.get(i).getDeliveryBegin().equals(startShipment)){
                    orders.remove(i);
                    continue;
                }
            }
            if (endShipment != null){
                if (!orders.get(i).getDeliveryFinish().equals(endShipment)){
                    orders.remove(i);
                    continue;
                }
            }
            if (contractNumber != null){
                if (!orders.get(i).getIdSpecification().getIdContract().getContractNumber().equals(contractNumber)){
                    orders.remove(i);
                    continue;
                }
            }
            i++;
        }
        return orders;
    }
}
