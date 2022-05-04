package com.example.sales_department.service;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.entity.Order;
import com.example.sales_department.entity.Specification;
import com.example.sales_department.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    ContractService contractService;

    public void add(Order order){
        orderRepository.save(order);
    }

    public List<Order> getAllByCustomerInn(BigInteger customerInn){
        Customer customer = customerService.getByInn(customerInn);
        List<Contract> contracts = contractService.getAllCustomerId(customer);
        List<Order> orders = new ArrayList<>();
        for(Contract contract: contracts){
            for(Specification specification: contract.getSpecifications()){
                orders.addAll(specification.getOrders());
            }
        }
        return orders;
    }
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public List<Order> findByAll(BigInteger inn, Long specification, LocalDate startShipment, LocalDate endShipment, Long contractNumber, LocalDate receiveDate){
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
            if (receiveDate != null){
                if(!orders.get(i).getReceiptDay().equals(receiveDate)){
                    orders.remove(i);
                    continue;
                }
            }
            i++;
        }
        return orders;
    }
}
