package com.john.pizzaawsenv.service;


import com.john.pizzaawsenv.model.Customer;
import com.john.pizzaawsenv.model.Order;
import com.john.pizzaawsenv.repository.CustomerRepository;
import com.john.pizzaawsenv.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Order> findByToppings(String topping) {
        List<Order> listOfOrders = new ArrayList<>();
        for (Order o : getAllOrders()){
            if(o.toString().toLowerCase().contains(topping.toLowerCase())){
                listOfOrders.add(o);
            }
        }
        return listOfOrders;
    }

    public void saveOrder(Order order){
        orderRepository.save(order);
    }


    public List<Order> getAllOrders() {
        List<Order> listOfOrders = new ArrayList<>();
        orderRepository.findAll().forEach(listOfOrders::add);
        return listOfOrders;
    }

    public Order updateOrder(Long id, Order order) {
        for (Order o : getAllOrders()) {
            if(o.getId() == id) {
                orderRepository.save(order);
            }
        }
        return order;
    }

    public Order patchOrder(Long id, Order order) {
        Order orderToUpdate = new Order();
        for (Order o : getAllOrders()) {
            if(o.getId() == id) {
                orderToUpdate = orderRepository.findById(id).get();
                if (order.getToppings() != null) {
                    orderToUpdate.setToppings(order.getToppings());
                }
                if (order.getStatus() != null) {
                    orderToUpdate.setStatus(order.getStatus());
                }
                if (order.getCrust() != null) {
                    orderToUpdate.setCrust(order.getCrust());
                }
                orderRepository.save(orderToUpdate);
            }
        }
        return order;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order getOrder(Long id) {
        Order order = orderRepository.findById(id).get();
        return order;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

}
