package com.example.cartoshare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DriverProfiles")
public class DriverProfile {
    // als ik onderstaande GenerratioType.. weghaal begint de nieuwe cyfering vanaf 1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String firstname;
    private String lastname;
    private int phoneNumber;
    private String email;
    private String username;
    private String password;

    @OneToOne
    Car car;
    @OneToMany(mappedBy = "driverProfile")
//    @JsonIgnore
    List<Ride> rides;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Ride> getRides() {
        return rides;
    }
//
//    public void setRides(List<Ride> rides) {
//        this.rides = rides;
//    }
}

