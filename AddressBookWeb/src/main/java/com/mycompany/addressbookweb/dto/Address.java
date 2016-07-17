package com.mycompany.addressbookweb.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class Address {

    private Integer id;

    @NotEmpty(message = "You must supply a first name")
    private String firstName;
    @NotEmpty(message = "You must supply a last name")
    private String lastName;
    @NotEmpty(message = "You must supply a street name")
    private String streetName;
    @NotEmpty(message = "You must supply a street number")
    private String streetNumber;
    @NotEmpty(message = "You must supply a city")
    private String city;
    @NotEmpty(message = "You must supply a state")
    private String state;
    @NotEmpty(message = "You must supply a zip code")
    private String zip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
