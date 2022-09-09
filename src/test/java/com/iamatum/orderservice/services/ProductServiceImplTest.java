package com.iamatum.orderservice.services;

import com.iamatum.orderservice.domain.Product;
import com.iamatum.orderservice.domain.ProductStatus;
import com.iamatum.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Testcontainers
@ActiveProfiles("testcontainer")
@ComponentScan(basePackageClasses = com.iamatum.orderservice.services.ProductServiceImpl.class)
class ProductServiceImplTest {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldUpdateQtyOnHand(){

        Product product = new Product();
        product.setProductStatus(ProductStatus.IN_STOCK);
        product.setQuantityOnHand(10);
        product.setDescription("IPhone");
        Product product1 = productService.addProduct(product);

        Product product2 = productService.updateQOH(product1.getId(), 60);

        assertThat(product2.getQuantityOnHand()).isEqualTo(60);


    }
}