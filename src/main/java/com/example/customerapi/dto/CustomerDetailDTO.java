package com.example.customerapi.dto;

import com.example.customerapi.entity.Customer;

public class CustomerDetailDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Long orderCount;

    public CustomerDetailDTO(Customer customer, Long orderCount) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.orderCount = orderCount;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getOrderCount() {
        return orderCount;
    }
}
