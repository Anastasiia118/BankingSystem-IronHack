package com.ironHacking.bankingSystem.models.utilities;

import jakarta.persistence.Embeddable;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Digits;

@Embeddable
public class Address {
    private String address;

    @Digits(integer = 6, fraction = 0)
    private Integer postalCode;

    private String city;

    private String country;

    public Address() {
    }

    public Address(String address, Integer postalCode, String city, String country) {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
