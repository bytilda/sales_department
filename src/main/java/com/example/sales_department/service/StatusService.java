package com.example.sales_department.service;

import com.example.sales_department.entity.Status;
import com.example.sales_department.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    public List<Status> getAll(){
        return statusRepository.findAll();
    }
}
