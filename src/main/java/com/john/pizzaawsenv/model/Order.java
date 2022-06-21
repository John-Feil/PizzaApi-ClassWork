package com.john.pizzaawsenv.model;


import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String crust;

    private String[] toppings;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ORDER_ID")
    private Customer customer;

    public Order() {
    }

    public Order(Long id, OrderStatus status, String crust, String[] toppings, Customer customer) {
        this.id = id;
        this.status = status;
        this.crust = crust;
        this.toppings = toppings;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getToppings() {
        return toppings;
    }


    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", crust='" + crust + '\'' +
                ", toppings=" + (Arrays.toString(toppings)) +
                ", customer=" + customer +
                '}';
    }
}

