package com.example.sales_department.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_nomenclature", schema = "sales_department_db")
public class ProductNomenclature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_cipher", nullable = false)
    private Long productCipher;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "weight")
    private Long weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "units")
    private Okei units;

    public Okei getUnits() {
        return units;
    }

    public void setUnits(Okei units) {
        this.units = units;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductCipher() {
        return productCipher;
    }

    public void setProductCipher(Long productCipher) {
        this.productCipher = productCipher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}