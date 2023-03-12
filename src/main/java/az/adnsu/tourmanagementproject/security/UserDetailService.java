package az.adnsu.tourmanagementproject.security;

import az.adnsu.tourmanagementproject.domain.User;
import az.adnsu.tourmanagementproject.repository.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) throw new UsernameNotFoundException("User not found");
        User user = userOptional.get();

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new ArrayList<>());
    }
}
