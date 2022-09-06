package com.iamatum.orderservice;

import com.iamatum.orderservice.domain.Address;
import com.iamatum.orderservice.domain.OrderHeader;
import com.iamatum.orderservice.domain.OrderLine;
import com.iamatum.orderservice.domain.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
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


        OrderLine ol = new OrderLine();
        ol.setOrderHeader(orderHeader);
        ol.setQuantityOrdered(5L);

        orderHeader.setOrderLines(Set.of(ol));

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getOrderStatus()).isEqualTo(OrderStatus.NEW);
        assertThat(orderHeader.getCreatedDate()).isNotNull();



        assertThat(ol.getOrderHeader()).isNotNull();
        assertThat(ol.getOrderHeader().getId()).isEqualTo(savedOrder.getId());


    }

}