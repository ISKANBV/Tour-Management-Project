package az.adnsu.tourmanagementproject.service.impl;

import az.adnsu.tourmanagementproject.domain.Tour;
import az.adnsu.tourmanagementproject.dto.TourDTO;
import az.adnsu.tourmanagementproject.dto.CreateTourDTO;
import az.adnsu.tourmanagementproject.repository.TourRepository;
import az.adnsu.tourmanagementproject.service.TourService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TourServiceImpl implements TourService {

    private final TourRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<TourDTO> getTourByDestination(String destination) {
        return repository.findAllByDestination(destination)
                .stream()
                .map(tour -> modelMapper.map(tour,TourDTO.class)).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public TourDTO createTour(CreateTourDTO dto) {
        Tour tour = modelMapper.map(dto,Tour.class);
        return modelMapper.map(repository.save(tour),TourDTO.class);
    }

    @Override
    public List<TourDTO> getTourList() {
        return repository.findAll()
                .stream()
                .map(tour -> modelMapper.map(tour,TourDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TourDTO getTour(Long id) {
        return modelMapper.map(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found")),TourDTO.class);
    }

    @SneakyThrows
    @Override
    public TourDTO updateTour(CreateTourDTO dto, Long tourId) {
        checkIfExists(tourId);
        Tour tour = modelMapper.map(dto,Tour.class);
        tour.setId(tourId);
        return modelMapper.map(repository.save(tour),TourDTO.class);
    }

    @Override
    public void deleteTour(Long id) {
        repository.deleteById(id);
    }

    private void checkIfExists(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour doesnt exist"));
    }

//    private Tour mapToEntity(CreateTourDTO dto) throws IOException {
//        String path = imageUploader.uploadImage(dto.getImage());
//
//        return Tour.builder()
//                .title(dto.getTitle())
//                .destination(dto.getDestination())
//                .overview(dto.getOverview())
//                .date(dto.getDate())
//                .groupSize(dto.getGroupSize())
//                .price(dto.getPrice())
//                .members(dto.getMembers())
//                .image(path)
//                .build();
//    }
}
