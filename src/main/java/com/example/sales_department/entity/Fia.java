package com.example.sales_department.entity;

import javax.persistence.*;

@Entity
@Table(name = "fias", schema = "sales_department_db")
public class Fia {
    @Id
    @Column(name = "consignee_address", nullable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}