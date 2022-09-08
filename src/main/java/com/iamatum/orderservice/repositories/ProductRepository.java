package com.iamatum.orderservice.repositories;

import com.iamatum.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByDescription(String description);

}
