package com.iamatum.orderservice.repositories;

import com.iamatum.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByDescription(String description);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional(readOnly = false)
    Optional<Product> findById(Long aLong);
}
