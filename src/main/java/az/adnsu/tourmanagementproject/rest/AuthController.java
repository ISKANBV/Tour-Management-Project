package az.adnsu.tourmanagementproject.rest;

import az.adnsu.tourmanagementproject.dto.AuthenticationDTO;
import az.adnsu.tourmanagementproject.dto.JWTResponseDTO;
import az.adnsu.tourmanagementproject.security.TokenProvider;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping
    public ResponseEntity<JWTResponseDTO> authenticate(@Valid @RequestBody AuthenticationDTO dto) {
        authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        String token = tokenProvider.createToken(dto.getUsername());
        JWTResponseDTO jwtResponseDTO = new JWTResponseDTO();
        jwtResponseDTO.setAccessToken(token);
        return ResponseEntity.ok(jwtResponseDTO);
    }
}
