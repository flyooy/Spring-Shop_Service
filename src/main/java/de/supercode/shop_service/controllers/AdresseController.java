package de.supercode.shop_service.controllers;

import de.supercode.shop_service.entities.Adresse;
import de.supercode.shop_service.services.AdresseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/addresses")
public class AdresseController {

    @Autowired
    private AdresseService adresseService;

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        Adresse adresse = adresseService.getAdresseById(id);
        if (adresse != null) {
            return new ResponseEntity<>(adresse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        List<Adresse> adresses = adresseService.getAllAdresses();
        return new ResponseEntity<>(adresses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Adresse> createAdresse(@Valid @RequestBody Adresse adresse) {
        Adresse createdAdresse = adresseService.createAdresse(adresse);
        return new ResponseEntity<>(createdAdresse, HttpStatus.CREATED);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationError(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMap.put(fieldName, message);
        });
        return errorMap;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(
            @PathVariable Long id,
            @RequestBody Adresse adresse
    ) {
        adresse.setId(id);
        try {
            Adresse updatedAdresse = adresseService.updateAdresse(adresse);
            return new ResponseEntity<>(updatedAdresse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        try {
            adresseService.deleteAdresse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}