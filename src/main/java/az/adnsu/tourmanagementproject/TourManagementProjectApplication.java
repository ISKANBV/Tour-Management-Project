package az.adnsu.tourmanagementproject;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TourManagementProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TourManagementProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LocalDate date = LocalDate.now();
        System.out.println(date);
    }
}
