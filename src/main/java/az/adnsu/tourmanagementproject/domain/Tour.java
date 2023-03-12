package az.adnsu.tourmanagementproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Column(length = 100)
    String title;

    @NotBlank
    @Column(length = 1000)
    String overview;

    @NotBlank
    @Column(length = 50)
    String destination;

    int duration;

    @Column(name = "group_size")
    byte groupSize;

    BigDecimal price;

    LocalDate date;

    String image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tour_user",
            joinColumns = @JoinColumn(name = "tour_id", referencedColumnName = "id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false))
    private Set<User> members = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;


}
