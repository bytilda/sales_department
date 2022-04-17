package com.example.sales_department.repository;

import com.example.sales_department.entity.Realization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealizationRepository extends JpaRepository<Realization, Long> {
}