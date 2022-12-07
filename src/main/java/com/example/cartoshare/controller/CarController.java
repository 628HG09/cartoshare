package com.example.cartoshare.controller;

import com.example.cartoshare.dto.CarDto;
import com.example.cartoshare.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {
private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars")
    public CarDto addCar(@RequestBody CarDto dto) {
        CarDto dto1 = carService.addCar(dto);
        return dto1;
    }

    @PutMapping("/cars/{id}")
    public CarDto updateDriverProfile(@PathVariable("id") Long id, @RequestBody CarDto dto) {
        carService.updateCar(id, dto);
        return dto;
    }

}
