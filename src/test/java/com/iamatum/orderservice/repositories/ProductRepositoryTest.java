package com.iamatum.orderservice.repositories;

import com.iamatum.orderservice.domain.Product;
import com.iamatum.orderservice.domain.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Testcontainers
@ActiveProfiles("testcontainer")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldCreateProduct() {

        Product product = new Product();
        product.setDescription("my product");
        product.setProductStatus(ProductStatus.IN_STOCK);
        product.setQuantityOnHand(30);
        Product saved = productRepository.save(product);
        assertThat(saved.getProductStatus()).isEqualTo(ProductStatus.IN_STOCK);

        saved.setQuantityOnHand(40);
        Product product1 = productRepository.saveAndFlush(saved);
        assertThat(product1.getQuantityOnHand()).isEqualTo(40);

    }

}