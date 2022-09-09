package com.iamatum.orderservice.bootstrap;

import com.iamatum.orderservice.domain.*;
import com.iamatum.orderservice.repositories.CustomerRepository;
import com.iamatum.orderservice.repositories.OrderHeaderRepository;
import com.iamatum.orderservice.services.ProductService;
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

    final ProductService productService;

    private void updateQoh() {

        Product product = new Product();
        product.setProductStatus(ProductStatus.IN_STOCK);
        product.setQuantityOnHand(10);
        product.setDescription("IPhone");
        Product product1 = productService.addProduct(product);

        Product product2 = productService.updateQOH(product1.getId(), 60);
        log.info("Qty on hand is {}", product2.getQuantityOnHand());


    }

    @Override
    public void run(String... args) throws Exception {

        updateQoh();

        Optional<OrderHeader> byId = orderHeaderRepository.findById(1L);
        if (byId.isEmpty()) {
            OrderHeader orderHeader = new OrderHeader();
            orderHeader.setOrderStatus(OrderStatus.NEW);
            OrderApproval orderApproval = new OrderApproval();
            orderApproval.setApprovedBy("marcus");

            orderHeader.setOrderApproval(orderApproval);

            orderHeaderRepository.saveAndFlush(orderHeader);

            log.info("No. of order records {}", orderHeaderRepository.count());


        }

        Customer customer = new Customer();
        customer.setCustomerName("De hema");
        Customer customer1 = customerRepository.saveAndFlush(customer);
        log.info("Customer {} has version {}", customer1.getCustomerName(), customer1.getVersion());

        customer1.setCustomerName("De hema 2");
        Customer customer2 = customerRepository.saveAndFlush(customer1);
        log.info("Customer {} has version {}", customer2.getCustomerName(), customer2.getVersion());

        customer2.setCustomerName("De hema 3");
        Customer customer3 = customerRepository.saveAndFlush(customer2);
        log.info("Customer {} has version {}", customer3.getCustomerName(), customer3.getVersion());


        customerRepository.deleteById(customer3.getId());


    }
}
