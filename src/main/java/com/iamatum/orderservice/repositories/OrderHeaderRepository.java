package com.iamatum.orderservice.repositories;

import com.iamatum.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader,Long> {
}
