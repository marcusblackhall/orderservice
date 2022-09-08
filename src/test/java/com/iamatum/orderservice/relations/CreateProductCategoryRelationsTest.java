package com.iamatum.orderservice.relations;


import com.iamatum.orderservice.domain.Product;
import com.iamatum.orderservice.repositories.CategoryRepository;
import com.iamatum.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Testcontainers
@ActiveProfiles("testcontainer")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateProductCategoryRelationsTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.5");


    @Test
    @Order(1)
    @Rollback(value = false)
    void shouldAddCategoryies(){

        Product prod = productRepository.findByDescription("prod1");
        assertThat(prod).isNotNull();

        assertThat(prod.getCategories()).isNotNull();




    }





}
