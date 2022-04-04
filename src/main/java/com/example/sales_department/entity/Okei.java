package com.example.sales_department.entity;

import javax.persistence.*;

@Entity
@Table(name = "okei", schema = "sales_department_db")
public class Okei {
    @Id
    @Lob
    @Column(name = "units", nullable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}