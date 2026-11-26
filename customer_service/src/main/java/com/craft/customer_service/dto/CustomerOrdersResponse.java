package com.craft.customer_service.dto;

import com.craft.customer_service.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrdersResponse {

    private Customer customer;

    private int totalOrders;

    private List<OrderDto> orders;
}