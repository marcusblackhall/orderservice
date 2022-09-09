package com.iamatum.orderservice.services;

import com.iamatum.orderservice.domain.Product;
import com.iamatum.orderservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product updateQOH(Long id, Integer qtyOnHand) {

        Product product = productRepository.findById(id).orElseThrow();
        product.setQuantityOnHand(qtyOnHand);
        return productRepository.saveAndFlush(product);
    }
}
