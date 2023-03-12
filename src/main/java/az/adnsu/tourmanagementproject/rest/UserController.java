package az.adnsu.tourmanagementproject.rest;

import az.adnsu.tourmanagementproject.dto.UserDTO;
import az.adnsu.tourmanagementproject.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUsersList() {
        return ResponseEntity.ok().body(service.getUsersList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getUser(id));
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserDTO dto) {
        service.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{username}/username")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(service.getUserByUsername(username));
    }

    @PutMapping("/{id}/add_manager_role")
    public ResponseEntity<Void> addManagerRole(@PathVariable Long id) {
        service.addManagerRole(id);
        return ResponseEntity.ok().build();
    }
}
