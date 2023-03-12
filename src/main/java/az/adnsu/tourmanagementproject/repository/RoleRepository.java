package az.adnsu.tourmanagementproject.repository;

import az.adnsu.tourmanagementproject.domain.Role;
import az.adnsu.tourmanagementproject.domain.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(RoleName name);
}
