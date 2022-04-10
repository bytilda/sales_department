package com.example.sales_department.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders", schema = "sales_department_db", indexes = {
        @Index(name = "ix_relationship92", columnList = "id_specification"),
        @Index(name = "ix_relationship1", columnList = "status_id")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_number", nullable = false)
    private Long orderNumber;

    @Column(name = "receipt_day", nullable = false)
    private LocalDate receiptDay;

    @Column(name = "delivery_begin", nullable = false)
    private LocalDate deliveryBegin;

    @Column(name = "delivery_finish", nullable = false)
    private LocalDate deliveryFinish;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_specification", nullable = false)
    private Specification idSpecification;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "order")
    private Set<ProductListInOrder> productListInOrders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idOrder")
    private Set<Realization> realizations = new LinkedHashSet<>();

    public Set<Realization> getRealizations() {
        return realizations;
    }

    public void setRealizations(Set<Realization> realizations) {
        this.realizations = realizations;
    }

    public Set<ProductListInOrder> getProductListInOrders() {
        return productListInOrders;
    }

    public void setProductListInOrders(Set<ProductListInOrder> productListInOrders) {
        this.productListInOrders = productListInOrders;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Specification getIdSpecification() {
        return idSpecification;
    }

    public void setIdSpecification(Specification idSpecification) {
        this.idSpecification = idSpecification;
    }

    public LocalDate getDeliveryFinish() {
        return deliveryFinish;
    }

    public void setDeliveryFinish(LocalDate deliveryFinish) {
        this.deliveryFinish = deliveryFinish;
    }

    public LocalDate getDeliveryBegin() {
        return deliveryBegin;
    }

    public void setDeliveryBegin(LocalDate deliveryBegin) {
        this.deliveryBegin = deliveryBegin;
    }

    public LocalDate getReceiptDay() {
        return receiptDay;
    }

    public void setReceiptDay(LocalDate receiptDay) {
        this.receiptDay = receiptDay;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}