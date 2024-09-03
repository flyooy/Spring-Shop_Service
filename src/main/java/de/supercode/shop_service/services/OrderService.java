package de.supercode.shop_service.services;

import de.supercode.shop_service.entities.Customer;
import de.supercode.shop_service.entities.Order;
import de.supercode.shop_service.repositories.CustomerRepository;
import de.supercode.shop_service.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Order createOrder(Long customerId, String status, LocalDate orderDate) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found."));
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(status);
        order.setOrderDate(orderDate);
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " not found."));
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new EntityNotFoundException("Order with ID " + orderId + " not found.");
        } else{
            orderRepository.deleteById(orderId);
        }
    }

    public Order updateOrder(Long orderId, Long customerId) {
        Order order = getOrder(orderId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found."));
        order.setCustomer(customer);
        return orderRepository.save(order);
    }
}
