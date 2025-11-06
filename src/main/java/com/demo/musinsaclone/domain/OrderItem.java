package com.demo.musinsaclone.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="order_items")
@Data
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(nullable=false) private int quantity;
    @Column(nullable=false) private int priceSnapshot; // 주문 시 단가(원)
    private String size;
    private String color;

    public void setOrder(Order order){ this.order = order; }
    public int lineTotal(){ return priceSnapshot * quantity; }

    // getter/setter
}
