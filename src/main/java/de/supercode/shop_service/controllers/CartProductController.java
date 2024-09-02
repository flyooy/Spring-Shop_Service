package de.supercode.shop_service.controllers;

import de.supercode.shop_service.entities.Adresse;
import de.supercode.shop_service.entities.CartProduct;
import de.supercode.shop_service.entities.Customer;
import de.supercode.shop_service.services.CartProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartproducts")
public class CartProductController {
    @Autowired
    private CartProductService cartProductService;

    @GetMapping
    public ResponseEntity<List<CartProduct>> getAllCartProducts() {
        List<CartProduct> cartProducts = cartProductService.getCartProducts();
        return new ResponseEntity<>(cartProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartProduct> getCartProduct(@PathVariable Long id) {
        CartProduct cartProduct = cartProductService.getCartProduct(id);
        if (cartProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(cartProduct, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartProduct> createCartProduct(@RequestBody CartProduct cartProduct) {
        CartProduct createdCartProduct = cartProductService.addCartProduct(cartProduct);
        return new ResponseEntity<>(createdCartProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartProduct> updateCartProduct(@PathVariable Long id, @RequestBody CartProduct cartProduct) {
        try {
            cartProduct.setId(id);
            CartProduct updatedCartProduct = cartProductService.updateCartProduct(cartProduct);
            return new ResponseEntity<>(updatedCartProduct, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartProduct(@PathVariable Long id) {
        try {
            cartProductService.deleteCartProduct(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
