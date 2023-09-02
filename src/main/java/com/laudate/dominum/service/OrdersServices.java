package com.laudate.dominum.service;

import com.laudate.dominum.entity.Orders;
import com.laudate.dominum.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServices {

    private OrdersRepository ordersRepository;

    public OrdersServices(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public void approveOrder(Orders orders) {
        ordersRepository.save(orders);
    }

    public Optional<Orders> findOrderById(int ordersId) { return ordersRepository.findById(ordersId); }
}
