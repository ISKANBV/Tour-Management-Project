package az.adnsu.tourmanagementproject.repository;

import az.adnsu.tourmanagementproject.domain.Tour;
import az.adnsu.tourmanagementproject.dto.TourDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour,Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"members"})
    List<Tour> findAllByDestination(String destination);

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"members"})
    List<Tour> findAll();

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"members"})
    Optional<Tour> findById(Long id);
}
