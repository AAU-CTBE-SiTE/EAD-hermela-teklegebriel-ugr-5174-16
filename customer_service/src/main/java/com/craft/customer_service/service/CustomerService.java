package com.craft.customer_service.service;

import com.craft.customer_service.entity.Customer;
import com.craft.customer_service.repo.CustomerRepo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    // Constructor injection
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    // Create customer
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id)
                .orElse(null);
    }

    // Update customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {

        Customer customer = customerRepo.findById(id)
                .orElse(null);

        if (customer == null) {
            return null;
        }

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());

        return customerRepo.save(customer);
    }

    // Delete customer
    public boolean deleteCustomer(Long id) {

        if (!customerRepo.existsById(id)) {
            return false;
        }

        customerRepo.deleteById(id);
        return true;
    }
}