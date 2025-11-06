package com.demo.musinsaclone.dto;

import com.demo.musinsaclone.domain.PaymentMethod;

public class OrderDtos {


    public record CreateOrderReq(
            String receiverName,
            String receiverPhone,
            String address1,
            String address2,
            String zipcode,
            PaymentMethod paymentMethod
    ) {}
    public record OrderIdRes(Long orderId) {}
}
