package de.supercode.shop_service.entities;

import jakarta.persistence.*;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adresse_id")
    private Long adresseId;
    private String street;
    private String hauseNumber;
    private String city;
    private String postalCode;

    public Adresse(String street, String hauseNumber, String city, String postalCode) {
        this.street = street;
        this.hauseNumber = hauseNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Adresse(){

    }

    public Long getId() {
        return adresseId;
    }
    public void setId(long adresseId) {
        this.adresseId = adresseId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHauseNumber() {
        return hauseNumber;
    }

    public void setHauseNumber(String hauseNumber) {
        this.hauseNumber = hauseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


}
