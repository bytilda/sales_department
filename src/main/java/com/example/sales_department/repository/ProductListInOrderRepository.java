package com.example.sales_department.repository;

import com.example.sales_department.entity.Order;
import com.example.sales_department.entity.ProductListInOrder;
import com.example.sales_department.entity.ProductListInOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductListInOrderRepository extends JpaRepository<ProductListInOrder, ProductListInOrderId> {
    List<ProductListInOrder> findAllById_IdOrder(Long orderId);
    void deleteAllById_IdOrder(Long orderId);
}