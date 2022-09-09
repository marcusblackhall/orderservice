package com.iamatum.orderservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Product extends BaseEntity {


    private Integer quantityOnHand;

    @EqualsAndHashCode.Exclude
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus productStatus;
    @EqualsAndHashCode.Exclude
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")

    )
    private Set<Category> categories;

//    public void  addCategory(Category category){
//
//        if (this.categories == null) categories = new HashSet<>();
//
//        categories.add(category);
//
//    }
}
