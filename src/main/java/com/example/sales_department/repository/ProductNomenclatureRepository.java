package com.example.sales_department.repository;

import com.example.sales_department.entity.ProductNomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNomenclatureRepository extends JpaRepository<ProductNomenclature, Long> {
}