package com.example.sales_department.repository;

import com.example.sales_department.entity.Fia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiaRepository extends JpaRepository<Fia, String> {
}