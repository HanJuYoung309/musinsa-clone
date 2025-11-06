package com.demo.musinsaclone.service;

import com.demo.musinsaclone.domain.Cart;
import com.demo.musinsaclone.domain.CartItem;
import com.demo.musinsaclone.domain.Product;
import com.demo.musinsaclone.domain.User;
import com.demo.musinsaclone.dto.CartDtos;
import com.demo.musinsaclone.repository.CartRepository;
import com.demo.musinsaclone.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getOrCreate(User user){
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(newCart(user)));
    }

    private Cart newCart(User user){
        Cart c = new Cart();
        c.setUser(user);
        return c;
    }

    public void addItem(User user, CartDtos.AddCartItemReq req){
        Cart cart = getOrCreate(user);
        Product product = productRepository.findById(req.productId())
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다."));

        // 동일 상품+옵션이면 수량만 증가
        CartItem same = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(req.productId())
                        && eq(i.getSize(), req.size()) && eq(i.getColor(), req.color()))
                .findFirst().orElse(null);

        if (same != null) {
            same.setQuantity(same.getQuantity() + req.quantity());
            return;
        }

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(req.quantity());
        item.setSize(req.size());
        item.setColor(req.color());
        item.setPriceSnapshot(product.getPrice()); // 현재 가격 스냅샷
        cart.addItem(item);
    }

    public void updateQuantity(User user, CartDtos.UpdateCartItemReq req){
        Cart cart = getOrCreate(user);
        CartItem item = cart.getItems().stream()
                .filter(i -> i.getId().equals(req.cartItemId()))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("장바구니 항목 없음"));
        if (req.quantity() <= 0) {
            cart.removeItem(item);
        } else {
            item.setQuantity(req.quantity());
        }
    }

    public void removeItem(User user, Long cartItemId){
        Cart cart = getOrCreate(user);
        CartItem item = cart.getItems().stream()
                .filter(i -> i.getId().equals(cartItemId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("장바구니 항목 없음"));
        cart.removeItem(item);
    }

    private boolean eq(String a, String b){
        return (a == null && b == null) || (a != null && a.equals(b));
    }
}