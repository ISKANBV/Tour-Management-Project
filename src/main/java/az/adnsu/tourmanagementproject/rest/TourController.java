package az.adnsu.tourmanagementproject.rest;

import az.adnsu.tourmanagementproject.dto.TourDTO;
import az.adnsu.tourmanagementproject.dto.CreateTourDTO;
import az.adnsu.tourmanagementproject.service.TourService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tours")
public class TourController {
    private final TourService service;

    @PostMapping
    public ResponseEntity<TourDTO> createTour(@RequestBody CreateTourDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTour(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TourDTO>> getTourList(){
        return ResponseEntity.ok().body(service.getTourList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTour(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getTour(id));
    }

    @PutMapping("/{tourId}")
    public ResponseEntity<TourDTO> updateTour(@Valid@RequestBody CreateTourDTO dto,
                                              @PathVariable Long tourId){
        return ResponseEntity.ok().body(service.updateTour(dto,tourId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id){
        service.deleteTour(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{destination}/destination")
    public ResponseEntity<List<TourDTO>> getTourByDestination(@PathVariable String destination){
        return ResponseEntity.ok().body(service.getTourByDestination(destination));
    }
}
