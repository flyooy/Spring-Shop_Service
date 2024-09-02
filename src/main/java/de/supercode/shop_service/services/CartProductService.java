package de.supercode.shop_service.services;

import de.supercode.shop_service.entities.Adresse;
import de.supercode.shop_service.entities.Cart;
import de.supercode.shop_service.entities.CartProduct;
import de.supercode.shop_service.entities.Product;
import de.supercode.shop_service.repositories.CartProductRepository;
import de.supercode.shop_service.repositories.CartRepository;
import de.supercode.shop_service.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductService {
    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private CartRepository  cartRepository;
    @Autowired
    private ProductRepository   productRepository;

    public List<CartProduct> getCartProducts() {
        return cartProductRepository.findAll();
    }
    public CartProduct getCartProduct(Long id) {
        return cartProductRepository.findById(id).orElseThrow();
    }

    public CartProduct addCartProduct(CartProduct cartProduct) {
        Cart cart = cartRepository.findById(cartProduct.getCart().getId()).orElse(null);
        if (cart != null) {
            cartProduct.setCart(cart);
        } else {
            throw new EntityNotFoundException("Cart with ID " + cartProduct.getCart().getId() + " not found.");
        }
        Product product = productRepository.findById(cartProduct.getProduct().getId()).orElse(null);
        if (product != null) {
            cartProduct.setProduct(product);
        } else {
            throw new EntityNotFoundException("Product with ID " + cartProduct.getProduct().getId() + " not found.");
        }

        return cartProductRepository.save(cartProduct);
    }
    public CartProduct updateCartProduct(CartProduct cartProduct) {

        if (!cartProductRepository.existsById(cartProduct.getId())) {
            throw new EntityNotFoundException("CartProduct with ID " + cartProduct.getId() + " not found.");
        }
        Cart cart = cartRepository.findById(cartProduct.getCart().getId()).orElse(null);
        if (cart != null) {
            cartProduct.setCart(cart);
        } else {
            throw new EntityNotFoundException("Cart with ID " + cartProduct.getCart().getId() + " not found.");
        }
        Product product = productRepository.findById(cartProduct.getProduct().getId()).orElse(null);
        if (product != null) {
            cartProduct.setProduct(product);
        } else {
            throw new EntityNotFoundException("Product with ID " + cartProduct.getProduct().getId() + " not found.");
        }
        return cartProductRepository.save(cartProduct);
    }
    public void deleteCartProduct(Long id) {
        if (cartProductRepository.existsById(id)) {
            cartProductRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("CartProduct with ID " + id + " not found.");
        }
    }
}
