package com.craft.order_service.service;

import com.craft.order_service.entity.Order;
import com.craft.order_service.repo.OrderRepo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    // Constructor injection
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    // CREATE ORDER
    public Order saveOrder(Order order) {

        // Let MongoDB generate the ID
        order.setId(null);

        return orderRepo.save(order);
    }

    // GET ALL ORDERS
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    // GET ORDER BY ID
    public Optional<Order> getOrderById(String id) {
        return orderRepo.findById(id);
    }

    // GET ORDERS BY CUSTOMER ID
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    // UPDATE ORDER
    public Order updateOrder(String id, Order updatedOrder) {

        Optional<Order> existingOrder =
                orderRepo.findById(id);

        if (existingOrder.isEmpty()) {
            return null;
        }

        Order order = existingOrder.get();

        order.setCustomerId(updatedOrder.getCustomerId());
        order.setProduct(updatedOrder.getProduct());
        order.setQuantity(updatedOrder.getQuantity());
        order.setPrice(updatedOrder.getPrice());

        return orderRepo.save(order);
    }

    // DELETE ORDER
    public boolean deleteOrder(String id) {

        if (!orderRepo.existsById(id)) {
            return false;
        }

        orderRepo.deleteById(id);
        return true;
    }
}