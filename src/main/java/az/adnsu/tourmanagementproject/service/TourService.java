package az.adnsu.tourmanagementproject.service;

import az.adnsu.tourmanagementproject.dto.TourDTO;
import az.adnsu.tourmanagementproject.dto.CreateTourDTO;
import java.util.List;

public interface TourService {

    List<TourDTO> getTourByDestination(String destination);

    TourDTO createTour(CreateTourDTO dto);

    List<TourDTO> getTourList();

    TourDTO getTour(Long id);

    TourDTO updateTour(CreateTourDTO dto , Long tourId);

    void deleteTour(Long id);
}
