package com.demo.musinsaclone.controller;

import com.demo.musinsaclone.domain.User;
import com.demo.musinsaclone.dto.CartDtos;
import com.demo.musinsaclone.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService){ this.cartService = cartService; }

    @PostMapping
    public void add(@AuthenticationPrincipal User user, @RequestBody CartDtos.AddCartItemReq req){
        cartService.addItem(user, req);
    }

    @PatchMapping
    public void update(@AuthenticationPrincipal User user, @RequestBody CartDtos.UpdateCartItemReq req){
        cartService.updateQuantity(user, req);
    }

    @DeleteMapping("/{cartItemId}")
    public void remove(@AuthenticationPrincipal User user, @PathVariable Long cartItemId){
        cartService.removeItem(user, cartItemId);
    }

    @GetMapping
    public Object get(@AuthenticationPrincipal User user){
        return cartService.getOrCreate(user); // 필요 시 DTO 변환
    }
}