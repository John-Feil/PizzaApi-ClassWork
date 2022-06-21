package com.john.pizzaawsenv.service;

import com.john.pizzaawsenv.model.Customer;
import com.john.pizzaawsenv.model.Order;
import com.john.pizzaawsenv.repository.CustomerRepository;
import com.john.pizzaawsenv.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomersService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll().forEach(customerList::add);
        return customerList;
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        for (Customer c : getAllCustomers()) {
            if (c.getId() == id) {
                customerRepository.save(customer);
            }
        }
        return customer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }

    public Set<Order> getOrdersByCustomer(Long id) {
        return orderRepository.findByCustomerId(id);
    }
}