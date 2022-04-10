package com.example.sales_department.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_list_in_realization", schema = "sales_department_db", indexes = {
        @Index(name = "ix_relationship84", columnList = "units")
})
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "units", nullable = false)
    private Okei units;

    public Okei getUnits() {
        return units;
    }

    public void setUnits(Okei units) {
        this.units = units;
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