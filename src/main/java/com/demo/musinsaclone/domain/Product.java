package com.demo.musinsaclone.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products", indexes = {
        @Index(name="ix_product_name", columnList = "name"),
        @Index(name="ix_product_brand", columnList = "brand_id"),
        @Index(name="ix_product_category", columnList = "category_id"),
        @Index(name="ix_product_popularity", columnList = "popularity"),
        @Index(name="ix_product_enabled", columnList = "enabled")
})
@Data
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=180)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="brand_id", nullable=false)
    private Brand brand;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @Column(nullable=false)
    private int price;

    private Integer discountPrice; // null이면 미할인

    @Column(length=2000)
    private String description;

    private String imageUrl; // S3/ CDN URL

    @ElementCollection
    @CollectionTable(name="product_sizes", joinColumns = @JoinColumn(name="product_id"))
    @Column(name="size", length=20)
    private List<String> sizes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="product_colors", joinColumns = @JoinColumn(name="product_id"))
    @Column(name="color", length=20)
    private List<String> colors = new ArrayList<>();

    @Column(nullable=false)
    private int stock = 0;

    @Column(nullable=false)
    private boolean enabled = true;

    @Column(nullable=false)
    private int popularity = 0; // 조회수/판매수 기반 가중치

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // 편의 메서드
    public int effectivePrice() { return discountPrice != null ? discountPrice : price; }

    // getter/setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public Brand getBrand() { return brand; }
    public Category getCategory() { return category; }
    public int getPrice() { return price; }
    public Integer getDiscountPrice() { return discountPrice; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public List<String> getSizes() { return sizes; }
    public List<String> getColors() { return colors; }
    public int getStock() { return stock; }
    public boolean isEnabled() { return enabled; }
    public int getPopularity() { return popularity; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }


}