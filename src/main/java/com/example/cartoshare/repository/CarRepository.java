package com.example.cartoshare.repository;

import com.example.cartoshare.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
