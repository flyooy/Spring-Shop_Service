package de.supercode.shop_service.repositories;

import de.supercode.shop_service.entities.Cart_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<Cart_Product, Long> {
}