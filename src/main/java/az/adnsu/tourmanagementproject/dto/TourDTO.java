package az.adnsu.tourmanagementproject.dto;

import az.adnsu.tourmanagementproject.domain.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDTO {

    private Long id;

    private String title;

    private String overview;

    private String destination;

    private int duration;

    private byte groupSize;

    private BigDecimal price;

    private LocalDate date;

    private String image;

    private Set<User> members;

    private LocalDateTime createdAt;
}
