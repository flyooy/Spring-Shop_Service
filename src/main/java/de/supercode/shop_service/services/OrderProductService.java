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

    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        Order order = orderRepository.findById(orderProduct.getOrder().getId())
                .orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderProduct.getOrder().getId() + " not found."));

        Product product = productRepository.findById(orderProduct.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + orderProduct.getProduct().getId() + " not found."));


        if (product.getQuantity() < orderProduct.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock for product ID " + product.getId()+ "-->");
        }

        orderProduct.setOrder(order);
        orderProduct.setProduct(product);

        product.setQuantity(product.getQuantity() - orderProduct.getQuantity());
        productRepository.save(product);
        return orderProductRepository.save(orderProduct);
    }
    public OrderProduct getOrderProduct(Long orderProductId) {
        return orderProductRepository.findById(orderProductId).orElseThrow();
    }
    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }
    public OrderProduct updateOrderProduct(Long orderProductId, OrderProduct updatedOrderProduct) {
        OrderProduct existingOrderProduct = getOrderProduct(orderProductId);


        if (updatedOrderProduct.getOrder() != null) {
            Order order = orderRepository.findById(updatedOrderProduct.getOrder().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Order with ID " + updatedOrderProduct.getOrder().getId() + " not found."));
            existingOrderProduct.setOrder(order);
        }

        if (updatedOrderProduct.getProduct() != null) {
            Product product = productRepository.findById(updatedOrderProduct.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product with ID " + updatedOrderProduct.getProduct().getId() + " not found."));
            existingOrderProduct.setProduct(product);
        }



        return orderProductRepository.save(existingOrderProduct);
    }


    public void deleteOrderProduct(Long orderProductId) {
        orderProductRepository.deleteById(orderProductId);
    }


}
