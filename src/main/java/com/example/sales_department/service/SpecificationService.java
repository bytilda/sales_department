package com.example.sales_department.service;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Specification;
import com.example.sales_department.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {
    @Autowired
    SpecificationRepository specificationRepository;

    public void add(Specification specification){
        specificationRepository.saveAndFlush(specification);
    }

    public List<Specification> getAll(){
        return specificationRepository.findAll();
    }

    public List<Specification> getAllByContract(Contract contract){
        return specificationRepository.findAllByIdContract(contract);
    }
}
