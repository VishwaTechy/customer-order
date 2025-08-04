package com.example.customerapi.controller;

import com.example.customerapi.dto.CustomerDetailDTO;
import com.example.customerapi.entity.Customer;
import com.example.customerapi.repository.CustomerRepository;
import com.example.customerapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrderRepository orderRepo;

    // GET /api/customers?page=0&size=10
    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Customer> customers = customerRepo.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(customers);
    }

    // GET /api/customers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        Integer customerId;
        try {
            customerId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid customer ID"));
        }

        Optional<Customer> customerOpt = customerRepo.findById(customerId);
        if (customerOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Customer not found"));
        }

        Customer customer = customerOpt.get();
        Long orderCount = orderRepo.countByUserId(customerId);
        CustomerDetailDTO dto = new CustomerDetailDTO(customer, orderCount);
        return ResponseEntity.ok(dto);
    }
}
