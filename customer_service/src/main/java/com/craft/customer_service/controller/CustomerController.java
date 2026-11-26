package com.craft.customer_service.controller;

import com.craft.customer_service.entity.Customer;
import com.craft.customer_service.service.CustomerService;
import com.craft.customer_service.dto.CustomerOrdersResponse;
import com.craft.customer_service.dto.OrderDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final RestClient restClient;

    // Constructor injection
    public CustomerController(
            CustomerService customerService,
            RestClient restClient) {

        this.customerService = customerService;
        this.restClient = restClient;
    }

    // CREATE CUSTOMER
    @PostMapping
    public ResponseEntity<Customer> saveCustomer(
            @RequestBody Customer customer) {

        Customer savedCustomer =
                customerService.saveCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCustomer);
    }

    // GET ALL CUSTOMERS
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        return ResponseEntity.ok(
                customerService.getAllCustomers()
        );
    }

    // GET CUSTOMER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long id) {

        Customer customer =
                customerService.getCustomerById(id);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    // UPDATE CUSTOMER
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        Customer updatedCustomer =
                customerService.updateCustomer(id, customer);

        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedCustomer);
    }

    // DELETE CUSTOMER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id) {

        boolean deleted =
                customerService.deleteCustomer(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // GET CUSTOMER AND THEIR ORDERS
    @GetMapping("/{id}/orders")
    public ResponseEntity<?> getCustomerOrders(
            @PathVariable Long id) {

        // Step 1: Check whether customer exists
        Customer customer =
                customerService.getCustomerById(id);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        try {

            // Step 2: Call Order Service using FULL URL
            List<OrderDto> orders = restClient.get()
                    .uri("http://localhost:9090/orders/customer/{id}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<OrderDto>>() {});

            // Step 3: Handle empty response
            if (orders == null) {
                orders = List.of();
            }

            // Step 4: Combine customer and orders
            CustomerOrdersResponse response =
                    new CustomerOrdersResponse(
                            customer,
                            orders.size(),
                            orders
                    );

            return ResponseEntity.ok(response);

        } catch (RestClientException exception) {

            // Step 5: Order Service unavailable or communication failed
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Order Service is currently unavailable.");
        }
    }
}
