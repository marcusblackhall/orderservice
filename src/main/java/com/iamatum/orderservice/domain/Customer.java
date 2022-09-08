package com.iamatum.orderservice.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer extends BaseEntity {

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private String customerName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orderHeaders;

    private String email;
    private String phone;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
