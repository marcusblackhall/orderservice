package com.iamatum.orderservice.domain;


import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class OrderApproval extends BaseEntity {

    private String approvedBy;

    @OneToOne
    private OrderHeader orderHeader;

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }



    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
