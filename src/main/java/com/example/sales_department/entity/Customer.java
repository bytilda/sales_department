package com.example.sales_department.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customer", schema = "sales_department_db")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "inn", nullable = false)
    private Long inn;

    @Column(name = "kpp", nullable = false)
    private Long kpp;

    @Column(name = "checking_account", nullable = false)
    private Long checkingAccount;

    @Column(name = "correspondent_account", nullable = false)
    private Long correspondentAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "okpo", nullable = false)
    private Okpo okpo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "legal_address", nullable = false)
    private Fia legalAddress;

    @OneToMany(mappedBy = "idCustomer")
    private Set<Contract> contracts = new LinkedHashSet<>();

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Fia getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(Fia legalAddress) {
        this.legalAddress = legalAddress;
    }

    public Okpo getOkpo() {
        return okpo;
    }

    public void setOkpo(Okpo okpo) {
        this.okpo = okpo;
    }

    public Long getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(Long correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }

    public Long getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(Long checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}