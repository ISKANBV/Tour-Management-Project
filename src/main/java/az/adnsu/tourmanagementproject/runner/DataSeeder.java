package az.adnsu.tourmanagementproject.runner;

import az.adnsu.tourmanagementproject.domain.Role;
import az.adnsu.tourmanagementproject.domain.User;
import az.adnsu.tourmanagementproject.domain.enumeration.RoleName;
import az.adnsu.tourmanagementproject.repository.RoleRepository;
import az.adnsu.tourmanagementproject.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (roleRepository.count() < 1) {
            Role userRole = new Role(null, RoleName.USER);
            Role adminRole = new Role(null, RoleName.ADMIN);
            Role managerRole = new Role(null, RoleName.MANAGER);
            roleRepository.saveAll(Arrays.asList(adminRole, userRole, managerRole));
        }

        if (userRepository.count() < 1) {
            User admin = new User();
            admin.setFirstName("Admin name");
            admin.setLastName("Lastname");
            admin.setUsername("admin");
            admin.setEmail("admin@test.com");
            admin.setPassword(passwordEncoder.encode("admin1"));
            admin.setRoles(new HashSet<>(roleRepository.findAll()));
            userRepository.save(admin);
        }

    }
}
