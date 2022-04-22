package com.example.sales_department.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "specification", schema = "sales_department_db")
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "id_contract")
    private Contract idContract;

    @Column(name = "application_number", nullable = false)
    private Long applicationNumber;

    @Column(name = "conclusion_date", nullable = false)
    private LocalDate conclusionDate;

    @Column(name = "is_terminated")
    private Boolean isTerminated;

    @OneToMany(mappedBy = "idSpecification")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idSpecification")
    private Set<ProductList> productLists = new LinkedHashSet<>();

    public Set<ProductList> getProductLists() {
        return productLists;
    }

    public void setProductLists(Set<ProductList> productLists) {
        this.productLists = productLists;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Boolean getIsTerminated() {
        return isTerminated;
    }

    public void setIsTerminated(Boolean isTerminated) {
        this.isTerminated = isTerminated;
    }

    public LocalDate getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(LocalDate conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public Long getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(Long applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public Contract getIdContract() {
        return idContract;
    }

    public void setIdContract(Contract idContract) {
        this.idContract = idContract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}