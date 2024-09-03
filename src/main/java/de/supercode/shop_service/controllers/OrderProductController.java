package de.supercode.shop_service.controllers;


import de.supercode.shop_service.entities.OrderProduct;
import de.supercode.shop_service.services.OrderProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-products")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;


    @PostMapping
    public ResponseEntity<OrderProduct> createOrderProduct(@RequestBody OrderProduct orderProduct) {
        try {
            OrderProduct createdOrderProduct = orderProductService.createOrderProduct(orderProduct);
            return new ResponseEntity<>(createdOrderProduct, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<OrderProduct>> getAllOrderProducts() {
        List<OrderProduct> orderProducts = orderProductService.getAllOrderProducts();
        return new ResponseEntity<>(orderProducts, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderProduct> getOrderProduct(@PathVariable Long id) {
        try {
            OrderProduct orderProduct = orderProductService.getOrderProduct(id);
            return new ResponseEntity<>(orderProduct, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderProduct> updateOrderProduct(@PathVariable Long id, @RequestBody OrderProduct orderProduct) {
        try {
            OrderProduct updatedOrderProduct = orderProductService.updateOrderProduct(id, orderProduct);
            return new ResponseEntity<>(updatedOrderProduct, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Long id) {
        try {
            orderProductService.deleteOrderProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
