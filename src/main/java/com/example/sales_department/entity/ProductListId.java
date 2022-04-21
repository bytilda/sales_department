package com.example.sales_department.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProductListId implements Serializable {
    private static final long serialVersionUID = 8210522856099140663L;
    @Column(name = "id_specification", nullable = false)
    private Long idSpecification;
    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdSpecification() {
        return idSpecification;
    }

    public void setIdSpecification(Long idSpecification) {
        this.idSpecification = idSpecification;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSpecification, idProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductListId entity = (ProductListId) o;
        return Objects.equals(this.idSpecification, entity.idSpecification) &&
                Objects.equals(this.idProduct, entity.idProduct);
    }
}