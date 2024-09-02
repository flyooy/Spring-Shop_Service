package de.supercode.shop_service.services;

import de.supercode.shop_service.entities.Adresse;
import de.supercode.shop_service.entities.Customer;
import de.supercode.shop_service.repositories.AdresseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {
    @Autowired
    private  AdresseRepository adresseRepository;

    public Adresse getAdresseById(Long id) {
        return adresseRepository.findById(id).orElse(null);
    }
    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }
    public Adresse createAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }
    public Adresse updateAdresse(Adresse adresse) {
        Optional<Adresse> existingAdresse = adresseRepository.findById(adresse.getId());
        if (existingAdresse.isPresent()) {
            return adresseRepository.save(adresse);
        } else {
            throw new EntityNotFoundException("Adresse with ID " + adresse.getId() + " not found.");
        }
    }
    public void deleteAdresse(Long id) {
        adresseRepository.deleteById(id);
    }
}
