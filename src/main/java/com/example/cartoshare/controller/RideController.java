package com.example.cartoshare.controller;

import com.example.cartoshare.dto.RideDto;
import com.example.cartoshare.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rides")
public class RideController {
    private final RideService rideService;

    public RideController(RideService rideService){
        this.rideService = rideService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addRide(@RequestBody RideDto rideDto, BindingResult br) {
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);

        } else {
            //moet het Long id zijn?
            Long rideId = rideService.addRide(rideDto);
            return new ResponseEntity<>(rideId, HttpStatus.CREATED);
        }
    }
    @PutMapping("/rides/{id}/{driverProfileId}")
    public void assignDriverProfileToRide(@PathVariable("id") Long id, @PathVariable("driverProfileId") Long driverProfileId) {
        rideService.assignDriverProfileToRide(id, driverProfileId);
    }
}
