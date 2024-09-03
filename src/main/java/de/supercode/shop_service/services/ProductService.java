package de.supercode.shop_service.services;

import de.supercode.shop_service.entities.Product;
import de.supercode.shop_service.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProduct(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            return productRepository.save(product);
        }
        else{
                throw new EntityNotFoundException("Product with ID " + product.getId() + " not found.");
            }
        }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }


}
