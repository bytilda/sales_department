package com.example.sales_department.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductListInRealizationId implements Serializable {
    private static final long serialVersionUID = -1590849228892073170L;
    @Column(name = "id_realization", nullable = false)
    private Long idRealization;
    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdRealization() {
        return idRealization;
    }

    public void setIdRealization(Long idRealization) {
        this.idRealization = idRealization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, idRealization);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductListInRealizationId entity = (ProductListInRealizationId) o;
        return Objects.equals(this.idProduct, entity.idProduct) &&
                Objects.equals(this.idRealization, entity.idRealization);
    }
}