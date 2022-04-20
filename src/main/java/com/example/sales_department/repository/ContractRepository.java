package com.example.sales_department.repository;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAllByIdCustomer(Customer customerId);
}