package com.demo.musinsaclone.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="orders", indexes = @Index(columnList = "user_id"))
@Data
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private OrderStatus status = OrderStatus.CREATED;

    @Column(nullable=false) private int totalPrice; // 총 결제 금액(원)
    @Column(nullable=false) private LocalDateTime createdAt = LocalDateTime.now();

    // 배송 정보(간단)
    private String receiverName;
    private String receiverPhone;
    private String address1;
    private String address2;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item){
        items.add(item);
        item.setOrder(this);
    }
    public void markPaid(){ this.status = OrderStatus.PAID; }
    public void cancel(){ this.status = OrderStatus.CANCELLED; }

    // getter/setter
}