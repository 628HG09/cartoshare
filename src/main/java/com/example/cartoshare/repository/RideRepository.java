package com.example.cartoshare.repository;

import com.example.cartoshare.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
