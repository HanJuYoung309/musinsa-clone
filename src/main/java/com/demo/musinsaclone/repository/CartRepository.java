package com.demo.musinsaclone.repository;


import com.demo.musinsaclone.domain.Cart;
import com.demo.musinsaclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}