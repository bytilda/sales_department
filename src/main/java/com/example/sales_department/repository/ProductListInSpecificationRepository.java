package com.example.sales_department.repository;

import com.example.sales_department.entity.ProductList;
import com.example.sales_department.entity.ProductListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListInSpecificationRepository extends JpaRepository<ProductList, ProductListId> {
}