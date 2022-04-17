package com.example.sales_department.repository;

import com.example.sales_department.entity.ProductListInOrder;
import com.example.sales_department.entity.ProductListInOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListInOrderRepository extends JpaRepository<ProductListInOrder, ProductListInOrderId> {
}