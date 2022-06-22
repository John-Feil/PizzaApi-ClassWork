package com.john.pizzaawsenv.controller;

import com.john.pizzaawsenv.PizzaawsEnvApplication;
import com.john.pizzaawsenv.model.Customer;
import com.john.pizzaawsenv.model.Order;
import com.john.pizzaawsenv.service.CustomersService;
import com.john.pizzaawsenv.service.OrdersService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




//@CrossOrigin("*")
@RestController
public class OrdersController {

    private static final Logger logger = LoggerFactory.getLogger(PizzaawsEnvApplication.class);

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CustomersService customersService;

    @GetMapping("/")
    public ResponseEntity<?> home(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrders(@RequestBody Order order) {
        logger.info("making a new order " + order.toString());
        for (Customer c : customersService.getAllCustomers()) {
            if (order.getCustomer().getId() == c.getId()){
                order.setCustomer(c);
            }
        }
        ordersService.saveOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(@RequestParam(required = false) String topping) {
        if (topping != null) {
            logger.info("finding by topping " + topping);
            ordersService.findByToppings(topping);
            return new ResponseEntity<>(ordersService.findByToppings(topping),HttpStatus.OK);
        }
        logger.info("finding all orders");
        return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getAOrder(@PathVariable Long id) {
        logger.info("finding order with id " + id);
        return new ResponseEntity<>(ordersService.getOrder(id), HttpStatus.OK);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> changeOrder(@PathVariable Long id, @RequestBody Order order) {
        logger.info("changing order with id " + id + " and order of " + order);
        return new ResponseEntity<>(ordersService.updateOrder(id, order), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        logger.info("deleting order with id " + id);
        ordersService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<?> patchOrder(@PathVariable Long id, @RequestBody Order order) {
        logger.info("patching order with id " + id + " and order of " + order);
        return new ResponseEntity<>(ordersService.patchOrder(id, order), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}/customer")
    public ResponseEntity<?> getCustomerByOrderId(@PathVariable Long id) {
        logger.info("Getting customer from order " + id);
        Order order = ordersService.getOrder(id);
        return new ResponseEntity<>(ordersService.getCustomerById(order.getCustomer().getId()) ,HttpStatus.OK);
    }

}

