package com.iamatum.orderservice;

import com.iamatum.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader,Long> {
}
