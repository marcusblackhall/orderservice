package com.iamatum.orderservice.domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@AttributeOverrides({
        @AttributeOverride(
                name = "shippingAddress.address",
                column = @Column(name = "shipping_address")
        ),
        @AttributeOverride(
                name = "shippingAddress.city",
                column = @Column(name = "shipping_city")
        ),
        @AttributeOverride(
                name = "shippingAddress.state",
                column = @Column(name = "shipping_state")
        ),
        @AttributeOverride(
                name = "shippingAddress.zipCode",
                column = @Column(name = "shipping_zip_code")
        ),
        @AttributeOverride(
                name = "billToAddress.address",
                column = @Column(name = "bill_to_address")
        ),
        @AttributeOverride(
                name = "billToAddress.city",
                column = @Column(name = "bill_to_city")
        ),
        @AttributeOverride(
                name = "billToAddress.state",
                column = @Column(name = "bill_to_state")
        ),
        @AttributeOverride(
                name = "billToAddress.zipCode",
                column = @Column(name = "bill_to_zip_code")
        )
})

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

public class OrderHeader extends BaseEntity {

    @OneToMany(mappedBy = "orderHeader", cascade=CascadeType.PERSIST)
    private Set<OrderLine> orderLines;

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public void setOrderApproval(OrderApproval orderApproval) {
        this.orderApproval = orderApproval;
    }

    @OneToOne
    private OrderApproval orderApproval;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Customer customer;

    @Embedded
    private Address shippingAddress;
    @Embedded
    private Address billToAddress;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    public void addOrderLine(OrderLine orderLine){

        if (orderLines == null) orderLines = new HashSet<>();

        orderLines.add(orderLine);
        orderLine.setOrderHeader(this);

    }

}
