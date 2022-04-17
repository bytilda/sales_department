package com.example.sales_department.service;

import com.example.sales_department.repository.ProductListInRealizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductListInRealizationService {
    @Autowired
    ProductListInRealizationRepository productListInRealizationRepository;
}
