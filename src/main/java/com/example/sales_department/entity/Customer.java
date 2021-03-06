package com.example.sales_department.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customer", schema = "sales_department_db", indexes = {
        @Index(name = "ix_relationship12", columnList = "legal_address"),
        @Index(name = "ix_relationship76", columnList = "okpo")
})
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "inn", nullable = false)
    private BigInteger inn;

    @Column(name = "kpp", nullable = false)
    private BigInteger kpp;

    @Column(name = "checking_account", nullable = false)
    private BigInteger checkingAccount;

    @Column(name = "correspondent_account", nullable = false)
    private BigInteger correspondentAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "okpo", nullable = false)
    private Okpo okpo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "legal_address", nullable = false)
    private Fia legalAddress;

    @OneToMany(mappedBy = "idCustomer", fetch = FetchType.EAGER)
    private Set<Contract> contracts = new LinkedHashSet<>();

}