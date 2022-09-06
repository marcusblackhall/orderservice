package com.iamatum.orderservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class OrderLine extends BaseEntity {

    @EqualsAndHashCode.Exclude
    private Long quantityOrdered;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private OrderHeader orderHeader;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Product product;


}
