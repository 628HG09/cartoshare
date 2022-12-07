package com.example.cartoshare.service;

import com.example.cartoshare.dto.CarDto;
import com.example.cartoshare.exception.RecordNotFoundException;
import com.example.cartoshare.model.Car;
import com.example.cartoshare.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
        private final CarRepository carRepository;

        public CarService(CarRepository carRepository){
            this.carRepository = carRepository;
        }


        public List<CarDto> getAllCars() {
            List<CarDto> dtos = new ArrayList<>();
            List<Car> cars = carRepository.findAll();
            for (Car rc : cars) {
                dtos.add(transferToDto(rc));
            }
            return dtos;
        }

        public CarDto addCar(CarDto carDto) {
            Car ac =  transferToCar(carDto);
            carRepository.save(ac);
            return carDto;
        }

        public Car transferToCar(CarDto dto){
            var car = new Car();

            car.setId(dto.getId());
            car.setBrand(dto.getBrand());
            car.setModel(dto.getModel());
            car.setLicensePlate(dto.getLicensePlate());
            car.setColor(dto.getColor());

            return car;
        }
        public CarDto transferToDto(Car car){
            var dto = new CarDto();

            dto.id = car.getId();
            dto.brand = car.getBrand();
            dto.model = car.getModel();
            dto.licensePlate = car.getLicensePlate();
            dto.color = car.getColor();


            return dto;
        }
    public void updateCar(Long id, CarDto carDto) {
        if(!carRepository.existsById(id)) {
            throw new RecordNotFoundException("No car found");
        }

        Car storedCar = carRepository.findById(id).orElse(null);
        storedCar.setId(carDto.getId());
        storedCar.setBrand(carDto.getBrand());
        storedCar.setModel(carDto.getModel());
        storedCar.setColor(carDto.getColor());
        storedCar.setLicensePlate(carDto.getLicensePlate());
        carRepository.save(storedCar);
    }
    }

