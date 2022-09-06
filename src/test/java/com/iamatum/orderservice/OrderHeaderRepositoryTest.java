package com.iamatum.orderservice;

import com.iamatum.orderservice.domain.*;
import com.iamatum.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Testcontainers
@ActiveProfiles("testcontainer")
class OrderHeaderRepositoryTest {


    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldCreateAnOrder(){

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomerName("my test order");

        Address address =
                Address.builder()
                        .address("Prinsengracht 10")
                        .city("Amsterdam")
                        .state("Noord Holland")
                        .zipCode("1002 VB")
                        .build();

        orderHeader.setShippingAddress(address);
        orderHeader.setOrderStatus(OrderStatus.NEW);

        Product product = new Product();
        product.setProductStatus(ProductStatus.IN_STOCK);
        product.setDescription("I Phone 13 ");
        productRepository.save(product);

        OrderLine ol = new OrderLine();
        ol.setOrderHeader(orderHeader);
        ol.setQuantityOrdered(5L);
        ol.setProduct(product);



        orderHeader.setOrderLines(Set.of(ol));

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getOrderStatus()).isEqualTo(OrderStatus.NEW);
        assertThat(orderHeader.getCreatedDate()).isNotNull();



        assertThat(ol.getOrderHeader()).isNotNull();
        assertThat(ol.getOrderHeader().getId()).isEqualTo(savedOrder.getId());


    }

}