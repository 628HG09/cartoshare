package com.example.cartoshare.service;

import com.example.cartoshare.dto.DriverProfileDto;
import com.example.cartoshare.dto.DriverProfileInputDto;
import com.example.cartoshare.exception.RecordNotFoundException;
import com.example.cartoshare.model.DriverProfile;
import com.example.cartoshare.repository.CarRepository;
import com.example.cartoshare.repository.DriverProfileRepository;
import com.example.cartoshare.repository.RideRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverProfileService {
    //dependecy injection nieuwe manier:
//    bij de testcode gaan we autowired gebruiken
    private final DriverProfileRepository driverProfileRepository;

    private final CarRepository carRepository;

    private final CarService carService;


    public DriverProfileService(DriverProfileRepository driverProfileRepository, CarRepository carRepository, CarService carService) {
        this.driverProfileRepository = driverProfileRepository;
        this.carRepository = carRepository;
        this.carService = carService;
    }
    // hier zit het probleem. Heeft te maken met de relatie en G&S in passagierProfiel
//    public List<PassagierProfielDto> transferPassagierProfielListToDtoList(List<passagierProfiel> passagierprofielen){
//        List<PassagierProfielDto> ppDtoList = new ArrayList<>();
//
//        for(passagierProfiel pp : passagierprofielen) {
//            PassagierProfielDto dto = transferToDto(pp);
//            if(pp.getRitverzoeken() != null){
//                dto.setRitverzoekDto(ritverzoekService.transferToDto(pp.getRitverzoeken()));
//            }
//            if(pp.getVoertuig() != null){
//                dto.setVoertuigDto(voertuigService.transferToDto(pp.getVoertuig()));
//            }
//            ppDtoList.add(dto);
//        }
//        return ppDtoList;
//    }

    public DriverProfileDto addDriverProfile(DriverProfileDto dto) {

        DriverProfile dp = transferToDriverProfile(dto);
        driverProfileRepository.save(dp);

        return transferToDto(dp);
    }

    // Vanuit de repository kunnen we een lijst van Televisions met een bepaalde brand krijgen, maar de communicatie
    // container tussen Service en Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit
    // moet een voor een, omdat de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<DriverProfileDto> getAllDriverProfiles() {
        List<DriverProfile> driverProfileList = driverProfileRepository.findAll();
        List<DriverProfileDto> driverProfileDtoList = new ArrayList<>();

        for(DriverProfile pp : driverProfileList) {
            DriverProfileDto dto = transferToDto(pp);
            driverProfileDtoList.add(dto);
        }
        return driverProfileDtoList;
    }

    // Vanuit de repository kunnen we een lijst van Televisions met een bepaalde brand krijgen, maar de communicatie
    // container tussen Service en Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit
    // moet een voor een, omdat de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<DriverProfileDto> getAllDriverProfilesByFirstname(String firstname) {
        List<DriverProfile> dpList = driverProfileRepository.findAllDriverProfilesByFirstnameEqualsIgnoreCase(firstname);
        List<DriverProfileDto> dpDtoList = new ArrayList<>();

        for(DriverProfile dp : dpList) {
            DriverProfileDto dto = transferToDto(dp);
            dpDtoList.add(dto);
        }
        return dpDtoList;
    }


    // Deze methode is inhoudelijk hetzelfde als het was in de vorige opdracht. Wat verandert is, is dat we nu checken
     //op optional.isPresent in plaats van optional.isEmpty en we returnen een TelevisionDto in plaats van een Television.
    public DriverProfileDto getDriverProfileById(Long id) {
        Optional<DriverProfile> passagierProfielOptional = driverProfileRepository.findById(id);
        if (passagierProfielOptional.isPresent()){
            DriverProfile driverProfile = passagierProfielOptional.get();
            return transferToDto(driverProfile);
        } else {
            throw new RecordNotFoundException("no driver found");
        }
    }


//    public Optional<passagierProfiel> getPassagierProfielById(Long id){
//        return repos.findById(id);
//
//        // de optional gaat zeker terugkomen in de eindopdracht
//    }

    public void deleteDriverProfile(@RequestBody Long id) {

        driverProfileRepository.deleteById(id);
    }



    public DriverProfileDto updateDriverProfile(Long id, DriverProfileInputDto newDriverProfile) {
//klopt!
        Optional<DriverProfile> driverProfileOptional = driverProfileRepository.findById(id);
        if (driverProfileOptional.isPresent()) {

            DriverProfile driverProfile1 = driverProfileOptional.get();

            driverProfile1.setFirstname(newDriverProfile.getFirstname());
            driverProfile1.setLastname(newDriverProfile.getLastname());
            driverProfile1.setPhoneNumber(newDriverProfile.getPhoneNumber());
            driverProfile1.setEmail(newDriverProfile.getEmail());
            driverProfile1.setUsername(newDriverProfile.username);
            driverProfile1.setPassword(newDriverProfile.getPassword());

            DriverProfile returnDriverProfile = driverProfileRepository.save(driverProfile1);

            return transferToDto(returnDriverProfile);

        } else {

            throw new RecordNotFoundException("geen bestuurder gevonden");

        }
    }
    // Dit is de vertaal methode van TelevisionInputDto naar Television.
    public DriverProfile transferToDriverProfile(DriverProfileInputDto dto){
        var driverProfile = new DriverProfile();

        driverProfile.setFirstname(dto.getFirstname());
        driverProfile.setLastname(dto.getLastname());
        driverProfile.setPhoneNumber(dto.getPhoneNumber());
        driverProfile.setEmail(dto.getEmail());
        driverProfile.setUsername(dto.username);
        driverProfile.setPassword(dto.getPassword());

        return driverProfile;
    }

    // Dit is de vertaal methode van Television naar TelevisionDto
    public DriverProfileDto transferToDto(DriverProfile driverProfile){
        DriverProfileDto dto = new DriverProfileDto();

        dto.setId(driverProfile.getId());
        dto.setFirstname(driverProfile.getFirstname());
        dto.setLastname(driverProfile.getLastname());
        dto.setPhoneNumber(driverProfile.getPhoneNumber());
        dto.setEmail(driverProfile.getEmail());
        dto.setUsername(driverProfile.getUsername());
        dto.setPassword(driverProfile.getPassword());

        return dto;
    }

    //  vanaf hier nieuwe les:

    public DriverProfile getDriverProfile(Long id){
        Optional <DriverProfile> opp = driverProfileRepository.findById(id);
        if (opp.isPresent()){
            return opp.get();
        }
        else {
            throw new RuntimeException("passagierprofiel niet gevonden");
        }
    }

    public List<DriverProfileDto> transferDriverProfileListToDtoList(List<DriverProfile> driverProfiles){
        List<DriverProfileDto> tvDtoList = new ArrayList<>();

        for(DriverProfile dp : driverProfiles) {
            DriverProfileDto dto = transferToDto(dp);
//            if(tv.getCiModule() != null){
//                dto.setCiModuleDto(ciModuleService.transferToDto(tv.getCiModule()));
//            }
            if(dp.getCar() != null){
                dto.setCarDto(carService.transferToDto(dp.getCar()));
            }
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }
    //klopt hieronder
    public void assignCarToDriverProfile(Long id, Long carId) {
        var optionalDriverProfile = driverProfileRepository.findById(id);
        var optionalCar = carRepository.findById(carId);

        if(optionalDriverProfile.isPresent() && optionalCar.isPresent()) {
            var driverProfile = optionalDriverProfile.get();
            var car = optionalCar.get();

            driverProfile.setCar(car);
            driverProfileRepository.save(driverProfile);
        } else {
            throw new RecordNotFoundException();
        }
    }

}

