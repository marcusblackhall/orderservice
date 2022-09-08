package com.iamatum.orderservice.repositories;


import com.iamatum.orderservice.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OrderApprovalRepository extends JpaRepository<OrderApproval,Long> {
}
