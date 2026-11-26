package com.craft.customer_service.repo;

import com.craft.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo
        extends JpaRepository<Customer, Long> {
}