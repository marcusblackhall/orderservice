package com.iamatum.orderservice.relations;


import com.iamatum.orderservice.domain.Address;
import com.iamatum.orderservice.domain.Customer;
import com.iamatum.orderservice.domain.OrderHeader;
import com.iamatum.orderservice.domain.OrderStatus;
import com.iamatum.orderservice.repositories.CustomerRepository;
import com.iamatum.orderservice.repositories.OrderHeaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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



}
