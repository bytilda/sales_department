package com.example.sales_department.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_list_in_order", schema = "sales_department_db")
public class ProductListInOrder {
    @EmbeddedId
    private ProductListInOrderId id;
    @MapsId("idOrder")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;

    @MapsId("idProduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_product", nullable = false)
    private ProductNomenclature idProduct;

    @Column(name = "amount", nullable = false)
    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ProductNomenclature getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(ProductNomenclature idProduct) {
        this.idProduct = idProduct;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductListInOrderId getId() {
        return id;
    }

    public void setId(ProductListInOrderId id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}