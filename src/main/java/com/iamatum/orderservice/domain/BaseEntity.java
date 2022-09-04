package com.iamatum.orderservice.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @EqualsAndHashCode.Exclude
    @CreationTimestamp()
    @Column(updatable = false)
    private Timestamp createdDate;

}
