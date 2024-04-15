package inc.side.bookstore.services.impl;

import inc.side.bookstore.dto.AuthenticateDto;
import inc.side.bookstore.dto.RegisterDto;
import inc.side.bookstore.entity.User;
import inc.side.bookstore.entity.enums.Role;
import inc.side.bookstore.repository.UserRepository;
import inc.side.bookstore.response.AuthenticationResponse;
import inc.side.bookstore.security.JwtService;
import inc.side.bookstore.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse registerUser(RegisterDto userDto) {
        System.out.println("..............");
        var user = User
                .builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticateUser(AuthenticateDto authenticateDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateDto.getUsername(),
                        authenticateDto.getPassword()
                )
        );
        var user =userRepository.findByUsername(authenticateDto.getUsername()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
