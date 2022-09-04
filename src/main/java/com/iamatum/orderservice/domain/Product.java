package com.iamatum.orderservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Product extends BaseEntity {


    @EqualsAndHashCode.Exclude
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @EqualsAndHashCode.Exclude
    private String description;
}
