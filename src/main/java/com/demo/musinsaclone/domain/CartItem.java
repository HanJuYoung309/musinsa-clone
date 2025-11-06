package com.demo.musinsaclone.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="cart_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id","product_id","size","color"}))
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;
    @Column(length = 20)
    private String size;   // 옵션
    @Column(length = 20)
    private String color;  // 옵션
    @Column(nullable = false)
    private int priceSnapshot; // 담을 당시 단가(원)

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}