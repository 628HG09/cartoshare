package com.example.cartoshare.controller;

import com.example.cartoshare.dto.DriverProfileDto;
import com.example.cartoshare.dto.DriverProfileInputDto;
import com.example.cartoshare.dto.IdInputDto;
import com.example.cartoshare.model.DriverProfile;
import com.example.cartoshare.service.DriverProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/driverprofiles")
public class DriverProfileController {
    private final DriverProfileService driverProfileService;

    public DriverProfileController(DriverProfileService driverprofileService) {
        this.driverProfileService = driverprofileService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addDriverProfile(@Valid @RequestBody DriverProfileDto driverProfileDto, BindingResult br) {
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
            Long driverProfileId = driverProfileService.addDriverProfile(driverProfileDto);
            return new ResponseEntity<>(driverProfileId, HttpStatus.CREATED);
        }
    }

    // Je ziet dat de return waarde van deze methode nu ResponseEntity<List<TelevisionDto>> is in plaats van <ResponseEntity<List<Television>>
    @GetMapping("")
    public ResponseEntity<List<DriverProfileDto>> getAllDriverProfiles(@RequestParam(value = "firstname", required = false) Optional<String> firstname) {

        List<DriverProfileDto> dtos;

        if (firstname.isEmpty()) {

            // We halen niet direct uit de repository een lijst met Televisions, maar we halen uit de service een lijst met TelevisionDto's
            dtos = driverProfileService.getAllDriverProfiles();

        } else {
            // Dit is ook een service methode geworden in plaats van direct de repository aan te spreken.
            dtos = driverProfileService.getAllDriverProfilesByFirstname(firstname.get());

        }

        return ResponseEntity.ok().body(dtos);

    }

    // De return waarde is ook hier een TelevisionDto in plaats van een Television
    @GetMapping("/{id}")
    public ResponseEntity<DriverProfileDto> getDriverProfile(@PathVariable("id") Long id) {

        // We spreken hier ook weer een service methode aan in plaats van direct de repository aan te spreken
        DriverProfileDto driverProfile = driverProfileService.getDriverProfileById(id);

        return ResponseEntity.ok().body(driverProfile);

    }

    // Hier veranderd niks aan de methode. We hebben niet meer de naam van de pathvariabele expliciet genoemd, omdat de
    // parameter-naam overeen komt met de naam van de pathvariabele.
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDriverProfile(@PathVariable Long id) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        driverProfileService.deleteDriverProfile(id);

        return ResponseEntity.noContent().build();

    }

    // Deze methode returned nu een ResponseEntity<TelevisionDto> in plaats van een ResponseEntity<Television> en deze
    // methode vraagt nu om een Long en een TelevisionInputDto in de parameters in plaats van een Long en een Television.
    @PutMapping("/{id}")
    public ResponseEntity<DriverProfileDto> updateDriverProfile(@PathVariable Long id, @RequestBody DriverProfileInputDto newDriverProfile) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        // Alle logica die hier eerst stond, is nu ook verplaatst naar de service laag.
        DriverProfileDto dto = driverProfileService.updateDriverProfile(id, newDriverProfile);

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/car")
    public void assignCarToDriverProfile(@PathVariable("id") Long id, @RequestBody IdInputDto input) {
        driverProfileService.assignCarToDriverProfile(id, input.id);
    }





//    @GetMapping("/passagierprofielen/{id}/ritverzoeken")
//    public ResponseEntity<Object> getRitverzoekenForPassagierProfiel(@PathVariable Long id ){
//        passagierProfiel p = service.getPassagierProfiel(id);
//        return new ResponseEntity<>(p.getRitverzoeken(), HttpStatus.OK);
//    }

}

//   volgende GET is direct connected met repsoitory methode!
//   @GetMapping("/passagierprofielen/oud")

//    public ResponseEntity<Object> getOldFarts(){
//       return new ResponseEntity<>(repos.findByDobBefore(LocalDate.of(1980,1, 1)), HttpStatus.OK);
//   }

