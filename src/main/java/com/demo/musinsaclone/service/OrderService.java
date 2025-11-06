package com.demo.musinsaclone.service;


import com.demo.musinsaclone.domain.*;
import com.demo.musinsaclone.dto.OrderDtos;
import com.demo.musinsaclone.repository.CartRepository;
import com.demo.musinsaclone.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public OrderDtos.OrderIdRes createOrderFromCart(User user, OrderDtos.CreateOrderReq req){
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("장바구니가 비어 있습니다."));

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("장바구니 항목이 없습니다.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setReceiverName(req.receiverName());
        order.setReceiverPhone(req.receiverPhone());
        order.setAddress1(req.address1());
        order.setAddress2(req.address2());
        order.setZipcode(req.zipcode());
        order.setPaymentMethod(req.paymentMethod());

        int total = 0;
        for (CartItem ci : cart.getItems()){
            OrderItem oi = new OrderItem();
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPriceSnapshot(ci.getPriceSnapshot());
            oi.setSize(ci.getSize());
            oi.setColor(ci.getColor());
            order.addItem(oi);
            total += oi.lineTotal();

            // (선택) 재고 차감 로직이 있다면 여기서 체크/차감
            // product.decreaseStock(ci.getQuantity());
        }
        order.setTotalPrice(total);

        // 주문 저장
        Order saved = orderRepository.save(order);

        // 장바구니 비우기
        cart.getItems().clear();

        return new OrderDtos.OrderIdRes(saved.getId());
    }

    public void markPaid(User user, Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문 없음"));
        if (!order.getUser().getId().equals(user.getId()))
            throw new SecurityException("본인 주문만 결제 가능");
        order.markPaid();
    }

    public void cancel(User user, Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문 없음"));
        if (!order.getUser().getId().equals(user.getId()))
            throw new SecurityException("본인 주문만 취소 가능");
        // (선택) 이미 배송/출고 등 상태 체크
        order.cancel();
        // (선택) 재고 롤백
    }
}