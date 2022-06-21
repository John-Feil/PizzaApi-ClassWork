package com.john.pizzaawsenv.controller;


import com.john.pizzaawsenv.PizzaawsEnvApplication;
import com.john.pizzaawsenv.model.Customer;
import com.john.pizzaawsenv.service.CustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin("*")
public class CustomersController {

    private static final Logger logger = LoggerFactory.getLogger(PizzaawsEnvApplication.class);

    @Autowired
    private CustomersService customersService;


    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        logger.info("Getting all customers");
        return new ResponseEntity<>(customersService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getACustomer(@PathVariable Long id) {
        logger.info("finding customer with id " + id);
        return new ResponseEntity<>(customersService.getCustomer(id),HttpStatus.OK);
    }

    @GetMapping("/customers/{id}/orders")
    public ResponseEntity<?> getAllOrdersFromCustomer(@PathVariable Long id) {
        logger.info("finding all orders for customer " + id);
        return new ResponseEntity<>(customersService.getOrdersByCustomer(id) ,HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> makeACustomer(@RequestBody Customer customer) {
        logger.info("Creating customer" + customer.toString());
        customersService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> changeCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        logger.info("Changing customer of id " + id + " to " + customer.toString());
        customersService.updateCustomer(id,customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        logger.info("deleting customer with id " + id);
        customersService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
