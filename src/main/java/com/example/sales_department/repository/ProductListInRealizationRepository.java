package com.example.sales_department.repository;

import com.example.sales_department.entity.ProductListInRealization;
import com.example.sales_department.entity.ProductListInRealizationId;
import com.example.sales_department.entity.Realization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductListInRealizationRepository extends JpaRepository<ProductListInRealization, ProductListInRealizationId> {
    List<ProductListInRealization> findAllByIdRealization(Realization realization);
    void deleteAllById_IdRealization(Long idRealization);
}