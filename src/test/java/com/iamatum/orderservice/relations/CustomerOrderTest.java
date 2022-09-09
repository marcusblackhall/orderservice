package com.iamatum.orderservice.relations;


import com.iamatum.orderservice.domain.*;
import com.iamatum.orderservice.repositories.CustomerRepository;
import com.iamatum.orderservice.repositories.OrderApprovalRepository;
import com.iamatum.orderservice.repositories.OrderHeaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@Testcontainers
@ActiveProfiles("testcontainer")
public class CustomerOrderTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    OrderApprovalRepository orderApprovalRepository;


    @Test
    void shouldCreateCustomerWithOrders(){

        Customer customer = new Customer();
        customer.setCustomerName("Fred Bassett");
        customer.setEmail("someone@gmail.com");
        customer.setPhone("056-665-5545");
        Address address = Address.builder()
                .city("New York")
                .zipCode("3433JJ")
                .state("Nevada")
                .build();

        customer.setAddress(address);

        Customer save = customerRepository.save(customer);

        assertNotNull(save.getId());

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);
        orderHeader.setOrderStatus(OrderStatus.NEW);

        OrderHeader header = orderHeaderRepository.save(orderHeader);

        assertThat(header.getCustomer()).isNotNull();


    }

    @Test
    void shouldDeleteOrderLinesOnDeleteOfOrder(){

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderStatus(OrderStatus.NEW);

        OrderLine ol = new OrderLine();
        ol.setQuantityOrdered(5L);

        orderHeader.addOrderLine(ol);

        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("me");
        orderHeader.setOrderApproval(orderApproval);

        OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);
        assertThat(savedOrder.getOrderLines().size()).isEqualTo(1);
        assertThat(savedOrder.getOrderApproval()).isNotNull();

        Long orderId = savedOrder.getId();

        orderHeaderRepository.deleteById(orderId);
        orderHeaderRepository.flush();

        assertThrows( EntityNotFoundException.class, () ->  {
             OrderHeader hdr = orderHeaderRepository.getById(orderId);
                assertNull(hdr);

        });






    }



}
