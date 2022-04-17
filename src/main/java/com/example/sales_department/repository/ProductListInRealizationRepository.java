package com.example.sales_department.repository;

import com.example.sales_department.entity.ProductListInRealization;
import com.example.sales_department.entity.ProductListInRealizationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListInRealizationRepository extends JpaRepository<ProductListInRealization, ProductListInRealizationId> {
}