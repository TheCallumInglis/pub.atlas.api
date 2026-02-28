package com.calluminglis.pubatlas.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDate;

import org.hibernate.type.ListType;

@Entity
public class Pub extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String name;

    public String area;

    public Double latitude;

    public Double longitude;

    public boolean visited;

    public LocalDate visit_date;

    public static long countAll() {
        return count();
    }
}