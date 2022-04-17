package com.example.sales_department.repository;

import com.example.sales_department.entity.Okpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OkpoRepository extends JpaRepository<Okpo, Long> {
}