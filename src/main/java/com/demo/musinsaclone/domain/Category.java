package com.demo.musinsaclone.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "categories", indexes = {
        @Index(name="ux_category_code", columnList = "code", unique = true),
        @Index(name="ix_category_parent", columnList = "parent_id")
})
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=64)
    private String name;

    @Column(nullable=false, length=64)
    private String code; // 예: men-top, women-shoes 등

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    // getter/setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public Category getParent() { return parent; }
    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }
    public void setParent(Category parent) { this.parent = parent; }
}