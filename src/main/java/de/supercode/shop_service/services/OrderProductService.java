package de.supercode.shop_service.services;

import de.supercode.shop_service.entities.Order;
import de.supercode.shop_service.entities.OrderProduct;
import de.supercode.shop_service.entities.Product;
import de.supercode.shop_service.repositories.OrderProductRepository;
import de.supercode.shop_service.repositories.OrderRepository;
import de.supercode.shop_service.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {

    @Autowired
    private  OrderProductRepository orderProductRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    public ProductRepository productRepository;

    public OrderProduct createOrderProduct(Long orderId, Long productId, double totalAmount) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " not found."));


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found."));


        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setTotalAmount(totalAmount);


        return orderProductRepository.save(orderProduct);
    }

    public OrderProduct getOrderProduct(Long orderProductId) {
        return orderProductRepository.findById(orderProductId).orElseThrow();
    }
    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }
    public OrderProduct updateOrderProduct(Long orderProductId, Long productId, Long orderId) {
        OrderProduct orderProduct = getOrderProduct(orderProductId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found."));
        orderProduct.setProduct(product);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " not found."));
        orderProduct.setOrder(order);
        return orderProductRepository.save(orderProduct);
    }

    public void deleteOrderProduct(Long orderProductId) {
        orderProductRepository.deleteById(orderProductId);
    }


}
