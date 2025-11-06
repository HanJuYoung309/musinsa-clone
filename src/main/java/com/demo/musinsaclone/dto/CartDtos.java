package com.demo.musinsaclone.dto;

public class CartDtos {

    public record AddCartItemReq(Long productId, int quantity, String size, String color) {}
    public record UpdateCartItemReq(Long cartItemId, int quantity) {}
}
