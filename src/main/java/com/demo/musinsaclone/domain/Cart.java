package com.demo.musinsaclone.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items= new ArrayList<>();

    public void addItem(CartItem item){
        items.add(item);
        item.setCart(this);
    }
    public void removeItem(CartItem item){
        items.remove(item);
        item.setCart(null);
    }



}
