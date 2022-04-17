package com.example.sales_department.repository;

import com.example.sales_department.entity.Okei;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OkeiRepository extends JpaRepository<Okei, String> {
}