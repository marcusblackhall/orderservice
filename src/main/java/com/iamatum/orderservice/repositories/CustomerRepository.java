package com.iamatum.orderservice.repositories;

import com.iamatum.orderservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
