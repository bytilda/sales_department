package com.example.sales_department.service;

import com.example.sales_department.entity.Fia;
import com.example.sales_department.repository.FiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiasService {
    @Autowired
    FiaRepository fiaRepository;

    public List<Fia> getAll(){
        return fiaRepository.findAll();
    }

    public Fia getById(String address){
        return fiaRepository.findById(address).orElse(null);
    }
}
