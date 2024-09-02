package de.supercode.shop_service.repositories;

import de.supercode.shop_service.entities.Order_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<Order_Product, Long> {
}