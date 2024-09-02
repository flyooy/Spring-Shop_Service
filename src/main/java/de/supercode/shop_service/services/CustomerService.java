package de.supercode.shop_service.services;

import de.supercode.shop_service.entities.Adresse;
import de.supercode.shop_service.entities.Customer;
import de.supercode.shop_service.repositories.AdresseRepository;
import de.supercode.shop_service.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdresseRepository adresseRepository;

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Customer createCustomer(Customer customer) {
        Adresse adresse = adresseRepository.findById(customer.getAdresse().getId()).orElse(null);
        if (adresse != null) {
            customer.setAdresse(adresse);
        } else {
            throw new EntityNotFoundException("Adresse with ID " + customer.getAdresse().getId() + " not found.");
        }
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Customer customer) {
        if (!customerRepository.existsById(customer.getId())) {
            throw new EntityNotFoundException("Customer with ID " + customer.getId() + " not found.");
        }
        Adresse adresse = adresseRepository.findById(customer.getAdresse().getId()).orElse(null);
        if (adresse != null) {
            customer.setAdresse(adresse);
        } else {
            throw new EntityNotFoundException("Adresse with ID " + customer.getAdresse().getId() + " not found.");
        }

        return customerRepository.save(customer);

    }
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}