package az.adnsu.tourmanagementproject.dto;

import az.adnsu.tourmanagementproject.domain.Role;
import az.adnsu.tourmanagementproject.domain.enumeration.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private RoleName name;

    public RoleDTO(Role role) {
        this.name = role.getName();
    }

}
