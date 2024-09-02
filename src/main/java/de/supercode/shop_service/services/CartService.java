package de.supercode.shop_service.services;

import de.supercode.shop_service.entities.Cart;
import de.supercode.shop_service.entities.Customer;
import de.supercode.shop_service.repositories.CartRepository;
import de.supercode.shop_service.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private  CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart with ID " + cartId + " not found."));
    }

    public Cart createCart(Long customerId) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            // Создаем новую корзину и связываем с клиентом
            Cart cart = new Cart();
            cart.setCustomer(customer);

            return cartRepository.save(cart);
        } else {
            throw new EntityNotFoundException("Customer with ID " + customerId + " not found.");
        }
    }
    public void deleteCart(Long cartId) {

        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
        } else {
            throw new EntityNotFoundException("Cart with ID " + cartId + " not found.");
        }
    }

    public Cart updateCart(Long cartId,Long customerId) {
        Cart cart = getCart(cartId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found."));
        cart.setCustomer(customer);
        return cartRepository.save(cart);
    }
}
