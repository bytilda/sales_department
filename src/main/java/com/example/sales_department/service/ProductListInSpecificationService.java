package com.example.sales_department.service;

import com.example.sales_department.entity.ProductList;
import com.example.sales_department.repository.ProductListInSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductListInSpecificationService {
    @Autowired
    ProductListInSpecificationRepository productListInSpecificationRepository;

    public void add(ProductList productList){
        productListInSpecificationRepository.saveAndFlush(productList);
    }

    public List<ProductList> getALLBySpecificationId(Long specificationId){
        return productListInSpecificationRepository.findAllById_IdSpecification(specificationId);
    }
}
