package az.adnsu.tourmanagementproject.service.impl;

import az.adnsu.tourmanagementproject.domain.Role;
import az.adnsu.tourmanagementproject.domain.User;
import az.adnsu.tourmanagementproject.domain.enumeration.RoleName;
import az.adnsu.tourmanagementproject.dto.UserDTO;
import az.adnsu.tourmanagementproject.repository.RoleRepository;
import az.adnsu.tourmanagementproject.repository.UserRepository;
import az.adnsu.tourmanagementproject.service.UserService;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Override
    public void signUp(UserDTO dto) {
        User user = mapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(new HashSet<>(Collections.singleton(roleRepository.findByName(RoleName.USER))));
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getUsersList() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(Long id) {
        return userRepository.findById(id).map(UserDTO::new).orElseThrow();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(user -> {
            if (user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()).contains(RoleName.ADMIN))
                throw new AccessDeniedException("Admin can't be deleted");

            userRepository.deleteById(id);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return mapper.map(userRepository.findByUsername(username).orElseThrow(), UserDTO.class);
    }

    @Override
    public void addManagerRole(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.getRoles().add(roleRepository.findByName(RoleName.MANAGER));
        userRepository.save(user);
    }
}

