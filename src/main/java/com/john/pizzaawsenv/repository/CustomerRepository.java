package com.john.pizzaawsenv.repository;

import com.john.pizzaawsenv.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
