package com.john.pizzaawsenv.repository;


import com.john.pizzaawsenv.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT * " +
            "FROM orders o, customers c " +
            "WHERE o.customer_customer_id =?1", nativeQuery = true)
    public Set<Order> findByCustomerId(Long Id);
}
