package az.adnsu.tourmanagementproject.dto;

import az.adnsu.tourmanagementproject.domain.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTourDTO {
    private String title;

    private String overview;

    private String destination;

    private int duration;

    private byte groupSize;

    private BigDecimal price;

    private LocalDate date;

    private Set<User> members;

    private String image;

}
