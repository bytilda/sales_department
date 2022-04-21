package com.example.sales_department.service;

import com.example.sales_department.entity.ProductNomenclature;
import com.example.sales_department.repository.ProductNomenclatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductNomenclatureService {
    @Autowired
    ProductNomenclatureRepository productNomenclatureRepository;

    public List<ProductNomenclature>  getAll(){
        return productNomenclatureRepository.findAll();
    }
}
