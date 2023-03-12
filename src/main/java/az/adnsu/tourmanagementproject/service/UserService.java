package az.adnsu.tourmanagementproject.service;

import az.adnsu.tourmanagementproject.dto.UserDTO;
import java.util.List;

public interface UserService {

    void signUp(UserDTO dto);

    List<UserDTO> getUsersList();

    UserDTO getUser(Long id);

    void deleteUser(Long id);

    UserDTO getUserByUsername(String username);

    void addManagerRole(Long id);
}
