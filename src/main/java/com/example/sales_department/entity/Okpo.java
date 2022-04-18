package com.example.sales_department.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "okpo", schema = "sales_department_db")
public class Okpo {
    @Id
    @Column(name = "okpo", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return id.toString();
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}