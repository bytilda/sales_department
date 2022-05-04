package com.example.sales_department.service;

import com.example.sales_department.entity.ProductListInRealization;
import com.example.sales_department.entity.Realization;
import com.example.sales_department.repository.ProductListInRealizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductListInRealizationService {
    @Autowired
    ProductListInRealizationRepository productListInRealizationRepository;

    public void save(ProductListInRealization productListInRealization){
        productListInRealizationRepository.save(productListInRealization);
    }

    public List<ProductListInRealization> getAllByRealization(Realization realization){
        return productListInRealizationRepository.findAllByIdRealization(realization);
    }

    public void deleteAllByRealization(Long id){
        productListInRealizationRepository.deleteAllById_IdRealization(id);
    }
}
