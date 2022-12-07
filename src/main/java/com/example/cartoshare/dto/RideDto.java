package com.example.cartoshare.dto;

import javax.validation.constraints.Past;
import java.time.LocalDate;

public class RideDto {
    private Long id;
    @Past
    private String pickupLocation;
    private String placeOfDeparture;
    private String destination;
    private String placeOfArrival;
    private LocalDate travelDate;
    private String pickupTime;
    private String estimatedTimedOfArrival;
    private String route;
    private int amountOfPeople;
    private int maxAmountOfPeople;
    private double pricePerPerson;
    private String rideDescription;
    private double totalPrice;
    private boolean automaticApproval;

    private DriverProfileDto driverProfileDto;

    public RideDto() {
    }

    public RideDto(Long id, String pickupLocation, String placeOfDeparture, String destination, String placeOfArrival, LocalDate travelDate, String pickupTime, String estimatedTimedOfArrival, String route, int amountOfPeople, int maxAmountOfPeople, double pricePerPerson, String rideDescription, double totalPrice, boolean automaticApproval) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.placeOfDeparture = placeOfDeparture;
        this.destination = destination;
        this.placeOfArrival = placeOfArrival;
        this.travelDate = travelDate;
        this.pickupTime = pickupTime;
        this.estimatedTimedOfArrival = estimatedTimedOfArrival;
        this.route = route;
        this.amountOfPeople = amountOfPeople;
        this.maxAmountOfPeople = maxAmountOfPeople;
        this.pricePerPerson = pricePerPerson;
        this.rideDescription = rideDescription;
        this.totalPrice = totalPrice;
        this.automaticApproval = automaticApproval;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getPlaceOfDeparture() {
        return placeOfDeparture;
    }

    public void setPlaceOfDeparture(String placeOfDeparture) {
        this.placeOfDeparture = placeOfDeparture;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPlaceOfArrival() {
        return placeOfArrival;
    }

    public void setPlaceOfArrival(String placeOfArrival) {
        this.placeOfArrival = placeOfArrival;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getEstimatedTimedOfArrival() {
        return estimatedTimedOfArrival;
    }

    public void setEstimatedTimedOfArrival(String estimatedTimedOfArrival) {
        this.estimatedTimedOfArrival = estimatedTimedOfArrival;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public int getMaxAmountOfPeople() {
        return maxAmountOfPeople;
    }

    public void setMaxAmountOfPeople(int maxAmountOfPeople) {
        this.maxAmountOfPeople = maxAmountOfPeople;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public String getRideDescription() {
        return rideDescription;
    }

    public void setRideDescription(String rideDescription) {
        this.rideDescription = rideDescription;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isAutomaticApproval() {
        return automaticApproval;
    }

    public void setAutomaticApproval(boolean automaticApproval) {
        this.automaticApproval = automaticApproval;
    }

    public DriverProfileDto getDriverProfileDto() {
        return driverProfileDto;
    }

    public void setDriverProfileDto(DriverProfileDto driverProfileDto) {
        this.driverProfileDto = driverProfileDto;
    }
    // boven staat alles publuc en beneden staan geen G&S
}
