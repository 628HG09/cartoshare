package com.example.cartoshare.model;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
public class Car {

    @Id
    @GeneratedValue
    Long id;
    private String brand;
    private String model;
    private String LicensePlate;
    private String color;

    @OneToOne(mappedBy = "car")
    DriverProfile driverprofile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        LicensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public DriverProfile getDriverprofile() {
        return driverprofile;
    }

    public void setDriverprofile(DriverProfile driverprofile) {
        this.driverprofile = driverprofile;
    }
}
