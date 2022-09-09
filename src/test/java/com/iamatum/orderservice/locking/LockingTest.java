package com.iamatum.orderservice.locking;


import com.iamatum.orderservice.domain.Address;
import com.iamatum.orderservice.domain.OrderHeader;
import com.iamatum.orderservice.repositories.OrderHeaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
public class LockingTest {

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Test
    void updateBilllingAddress(){

        Optional<OrderHeader> byId = orderHeaderRepository.findById(1L);

        if (byId.isPresent()){

            OrderHeader orderHeader = byId.get();
            Address billToAddress = new Address();
            billToAddress.setAddress("Smith road");
            billToAddress.setCity("Basel");
            billToAddress.setState("Cheshire");
            orderHeader.setBillToAddress(billToAddress);

            OrderHeader orderHeader1 = orderHeaderRepository.saveAndFlush(orderHeader);
            assertThat(orderHeader1.getBillToAddress().getCity()).isEqualTo("Basel");


        }


    }

}
