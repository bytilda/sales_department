package com.example.sales_department.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@Table(name = "product_list", schema = "sales_department_db")
@NoArgsConstructor
@AllArgsConstructor
public class ProductList {
    @EmbeddedId
    private ProductListId id;

    @MapsId("idSpecification")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_specification", nullable = false)
    private Specification idSpecification;

    @MapsId("idProduct")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_product", nullable = false)
    private ProductNomenclature idProduct;

    @Column(name = "price", nullable = false, precision = 20, scale = 2)
    private BigDecimal price;

    @Column(name = "amount")
    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductNomenclature getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(ProductNomenclature idProduct) {
        this.idProduct = idProduct;
    }

    public Specification getIdSpecification() {
        return idSpecification;
    }

    public void setIdSpecification(Specification idSpecification) {
        this.idSpecification = idSpecification;
    }

    public ProductListId getId() {
        return id;
    }

    public void setId(ProductListId id) {
        this.id = id;
    }
}