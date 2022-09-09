package com.iamatum.orderservice.services;

import com.iamatum.orderservice.domain.Product;

public interface ProductService {

    Product addProduct(Product product);

    Product updateQOH(Long id, Integer qtyOnHand);
}
