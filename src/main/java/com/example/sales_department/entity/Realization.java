package com.example.sales_department.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "realization", schema = "sales_department_db")
public class Realization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order idOrder;

    @Column(name = "payment_status", nullable = false)
    private Boolean paymentStatus = false;

    @Column(name = "facted_time_of_shipment")
    private Instant factedTimeOfShipment;

    @Column(name = "upd_number", nullable = false)
    private Long updNumber;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consignee_address", nullable = false)
    private Fia consigneeAddress;

    @OneToMany(mappedBy = "idRealization")
    private Set<ProductListInRealization> productListInRealizations = new LinkedHashSet<>();

    @Column(name = "receiving_date")
    private LocalDate receivingDate;

    public LocalDate getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(LocalDate receivingDate) {
        this.receivingDate = receivingDate;
    }

    public Set<ProductListInRealization> getProductListInRealizations() {
        return productListInRealizations;
    }

    public void setProductListInRealizations(Set<ProductListInRealization> productListInRealizations) {
        this.productListInRealizations = productListInRealizations;
    }

    public Fia getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(Fia consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getUpdNumber() {
        return updNumber;
    }

    public void setUpdNumber(Long updNumber) {
        this.updNumber = updNumber;
    }

    public Instant getFactedTimeOfShipment() {
        return factedTimeOfShipment;
    }

    public void setFactedTimeOfShipment(Instant factedTimeOfShipment) {
        this.factedTimeOfShipment = factedTimeOfShipment;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}