package com.example.sales_department.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductListInOrderId implements Serializable {
    private static final long serialVersionUID = -1396979152940314506L;
    @Column(name = "id_product", nullable = false)
    private Long idProduct;
    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductListInOrderId entity = (ProductListInOrderId) o;
        return Objects.equals(this.idOrder, entity.idOrder) &&
                Objects.equals(this.idProduct, entity.idProduct);
    }
}