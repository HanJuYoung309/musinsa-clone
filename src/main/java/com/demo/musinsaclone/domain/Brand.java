package com.demo.musinsaclone.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "brands", indexes = @Index(name = "ux_brand_name", columnList = "name", unique = true))
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=80)
    private String name;

    @Column(length=255)
    private String description;

    // getter/setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
}