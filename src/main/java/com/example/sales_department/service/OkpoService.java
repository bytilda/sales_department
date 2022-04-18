package com.example.sales_department.service;

import com.example.sales_department.entity.Okpo;
import com.example.sales_department.repository.OkpoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OkpoService {
    @Autowired
    OkpoRepository okpoRepository;

    public List<Okpo> getAll(){
        return okpoRepository.findAll();
    }
}
