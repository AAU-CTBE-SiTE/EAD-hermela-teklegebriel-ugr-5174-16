package com.craft.order_service.repo;

import com.craft.order_service.entity.Order;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepo
        extends MongoRepository<Order, String> {

    List<Order> findByCustomerId(Long customerId);
}