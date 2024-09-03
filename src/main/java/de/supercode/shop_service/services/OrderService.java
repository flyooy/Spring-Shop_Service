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

    public Order createOrder(Order order) {

        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + order.getCustomer().getId() + " not found."));
        order.setCustomer(customer);

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
    public Order updateOrder(Long orderId, Order order) {

    Order existingOrder = orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " not found."));

        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
}
}
