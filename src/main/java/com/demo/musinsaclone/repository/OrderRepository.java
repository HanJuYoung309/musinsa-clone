package com.demo.musinsaclone.repository;


import com.demo.musinsaclone.domain.Order;
import com.demo.musinsaclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByIdDesc(User user);
}
