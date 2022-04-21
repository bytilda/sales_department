package com.example.sales_department.service;

import com.example.sales_department.entity.Specification;
import com.example.sales_department.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecificationService {
    @Autowired
    SpecificationRepository specificationRepository;

    public void add(Specification specification){
        specificationRepository.saveAndFlush(specification);
    }
}
