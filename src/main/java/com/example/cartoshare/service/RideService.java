package com.example.cartoshare.service;

import com.example.cartoshare.dto.RideDto;
import com.example.cartoshare.exception.RecordNotFoundException;
import com.example.cartoshare.model.Ride;
import com.example.cartoshare.repository.DriverProfileRepository;
import com.example.cartoshare.repository.RideRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {
    private final RideRepository rideRepository;

    private final DriverProfileRepository driverProfileRepository;

    private final DriverProfileService driverProfileService;

    public RideService(RideRepository rideRepository, DriverProfileRepository driverProfileRepository, DriverProfileService driverProfileService) {
        this.rideRepository = rideRepository;
        this.driverProfileRepository = driverProfileRepository;
        this.driverProfileService = driverProfileService;
    }

//    public Long addRide(RideDto rideDto) {
//        Ride ride = new Ride();
//
//        ride.setPickupLocation(rideDto.getPickupLocation);
//        ride.setPlaceOfArrival(rideDto.getPlaceOfDeparture);
//        ride.setDestination(rideDto.getDestination);
//        ride.setPlaceOfArrival(rideDto.getPlaceOfArrival);
//        ride.setTravelDate(rideDto.getTravelDate);
//        ride.setPickupTime(rideDto.getPickupTime);
//        ride.setEstimatedTimedOfArrival(rideDto.getEstimatedTimedOfArrival);
//        ride.setRoute(rideDto.getRoute);
//        ride.setAmountOfPeople(rideDto.getAmountOfPeople);
//        ride.setMaxAmountOfPeople(rideDto.getMaxAmountOfPeople);
//        ride.setPricePerPerson(rideDto.getPricePerPerson);
//        ride.setRideDescription(rideDto.getRideDescription);
//        ride.setTotalPrice(rideDto.getTotalPrice);
//        ride.setAutomaticApproval(rideDto.getautomaticApproval);
//
//        Ride savedRide = this.rideRepository.save(ride);
//
//        return savedRide.getId();
//
//    }
public RideDto addRide(RideDto dto) {

    Ride tv = transferToRide(dto);
    rideRepository.save(tv);

    return transferToDto(tv);
}

    public RideDto getRideById(Long id) {

        if (rideRepository.findById(id).isPresent()){
            Ride tv = rideRepository.findById(id).get();
            RideDto dto =transferToDto(tv);
            if(tv.getDriverProfile() != null){
                dto.setDriverProfileDto(driverProfileService.transferToDto(tv.getDriverProfile()));
            }
//            if(tv.getRemoteController() != null){
//                dto.setRemoteControllerDto(remoteControllerService.transferToDto(tv.getRemoteController()));
//            }

            return transferToDto(tv);
        } else {
            throw new RecordNotFoundException("geen rit gevonden");
        }
    }
//    public RitDto addRit(RitDto dto) {
//
//        Rit tv = transferToRit(dto);
//        ritRepository.save(tv);
//
//        return transferToDto(tv);
//    }

    public Ride transferToRide(RideDto dto){
        var ride = new Ride();

        ride.setPickupLocation(dto.getPickupLocation());
        ride.setPlaceOfDeparture(dto.getPlaceOfDeparture());
        ride.setDestination(dto.getDestination());
        ride.setPlaceOfArrival(dto.getPlaceOfArrival());
        ride.setTravelDate(dto.getTravelDate());
        ride.setPickupTime(dto.getPickupTime());
        ride.setEstimatedTimedOfArrival(dto.getEstimatedTimedOfArrival());
        ride.setRoute(dto.getRoute());
        ride.setAmountOfPeople(dto.getAmountOfPeople());
        ride.setMaxAmountOfPeople(dto.getMaxAmountOfPeople());
        ride.setPricePerPerson(dto.getPricePerPerson());
        ride.setRideDescription(dto.getRideDescription());
        ride.setTotalPrice(dto.getTotalPrice());
        //klopt deze bij automaticAp[proval?
        ride.setAutomaticApproval(dto.isAutomaticApproval());

        return ride;
    }

    public RideDto transferToDto(Ride ride){
        RideDto dto = new RideDto();

        dto.setId(ride.getId());
        dto.setPickupLocation(ride.getPickupLocation());
        dto.setPlaceOfDeparture(ride.getPlaceOfDeparture());
        dto.setDestination(ride.getDestination());
        dto.setPlaceOfArrival(ride.getPlaceOfArrival());
        dto.setTravelDate(ride.getTravelDate());
        dto.setPickupTime(ride.getPickupTime());
        dto.setEstimatedTimedOfArrival(ride.getEstimatedTimedOfArrival());
        dto.setRoute(ride.getRoute());
        dto.setAmountOfPeople(ride.getAmountOfPeople());
        dto.setMaxAmountOfPeople(ride.getMaxAmountOfPeople());
        dto.setPricePerPerson(ride.getPricePerPerson());
        dto.setRideDescription(ride.getRideDescription());
        dto.setTotalPrice(ride.getTotalPrice());
        dto.setAutomaticApproval(ride.isAutomaticApproval());

        if(ride.getDriverProfile() != null){
            //bestuurderService begon met een B
            dto.setDriverProfileDto(DriverProfileService.transferToDto(ride.getDriverProfile()));
        }

        return dto;
    }



public void assignDriverProfileToRide(Long id, Long driverProfileId) {
    var optionalRide = rideRepository.findById(id);
    var optionalDriverProfile = driverProfileRepository.findById(driverProfileId);

    if (optionalRide.isPresent() && optionalDriverProfile.isPresent()) {
        var ride = optionalRide.get();
        var driverProfile = optionalDriverProfile.get();

        ride.setDriverProfile(driverProfile);
        rideRepository.save(ride);
    } else {
        throw new RecordNotFoundException();
    }
}
}

