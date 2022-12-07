package com.example.cartoshare.repository;

import com.example.cartoshare.model.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DriverProfileRepository extends JpaRepository<DriverProfile, Long> {
    //Methode laat passagierszien die geborden zijn voor... hoeven geen body. Wel in controller
//    List<DriverProfile> findByDobBefore(LocalDate date);

    List<DriverProfile> findAllDriverProfilesByFirstnameEqualsIgnoreCase(String firstname);
}
