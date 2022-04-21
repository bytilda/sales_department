package com.example.sales_department.service;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    public void add(Contract contract){
        contractRepository.save(contract);
    }

    public List<Contract> getAll(){
        return contractRepository.findAll();
    }

    public List<Contract> getAllCustomerId(Customer customerId) {return contractRepository.findAllByIdCustomer(customerId);}
}
