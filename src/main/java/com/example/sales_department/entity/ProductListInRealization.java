package com.example.sales_department.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_list_in_realization", schema = "sales_department_db")
public class ProductListInRealization {
    @EmbeddedId
    private ProductListInRealizationId id;

    @MapsId("idRealization")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_realization", nullable = false)
    private Realization idRealization;

    @MapsId("idProduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_product", nullable = false)
    private ProductNomenclature idProduct;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "price", nullable = false, precision = 20, scale = 2)
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public Realization getIdRealization() {
        return idRealization;
    }

    public void setIdRealization(Realization idRealization) {
        this.idRealization = idRealization;
    }

    public ProductListInRealizationId getId() {
        return id;
    }

    public void setId(ProductListInRealizationId id) {
        this.id = id;
    }
}