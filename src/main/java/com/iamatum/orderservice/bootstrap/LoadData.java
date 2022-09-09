package com.iamatum.orderservice.bootstrap;

import com.iamatum.orderservice.domain.Customer;
import com.iamatum.orderservice.domain.OrderApproval;
import com.iamatum.orderservice.domain.OrderHeader;
import com.iamatum.orderservice.domain.OrderStatus;
import com.iamatum.orderservice.repositories.CustomerRepository;
import com.iamatum.orderservice.repositories.OrderHeaderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class LoadData implements CommandLineRunner {

    final OrderHeaderRepository orderHeaderRepository;
    final CustomerRepository customerRepository;
    @Override
    public void run(String... args) throws Exception {

        Optional<OrderHeader> byId = orderHeaderRepository.findById(1L);
        if (byId.isEmpty()){
            OrderHeader orderHeader = new OrderHeader();
            orderHeader.setOrderStatus(OrderStatus.NEW);
            OrderApproval orderApproval = new OrderApproval();
            orderApproval.setApprovedBy("marcus");

            orderHeader.setOrderApproval(orderApproval);

            orderHeaderRepository.saveAndFlush(orderHeader);

            log.info("No. of order records {}",orderHeaderRepository.count());


        }

        Customer customer = new Customer();
        customer.setCustomerName("De hema");
        Customer customer1 = customerRepository.saveAndFlush(customer);
        log.info("Customer {} has version {}",customer1.getCustomerName(),customer1.getVersion());

        customer1.setCustomerName("De hema 2");
        Customer customer2 = customerRepository.saveAndFlush(customer1);
        log.info("Customer {} has version {}",customer2.getCustomerName(),customer2.getVersion());

        customer2.setCustomerName("De hema 3");
        Customer customer3 = customerRepository.saveAndFlush(customer2);
        log.info("Customer {} has version {}",customer3.getCustomerName(),customer3.getVersion());


        customerRepository.deleteById(customer3.getId());




    }
}
