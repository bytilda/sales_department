package com.example.sales_department.service;

import com.example.sales_department.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;
}
