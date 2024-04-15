package inc.side.bookstore.controller;

import inc.side.bookstore.dto.AuthenticateDto;
import inc.side.bookstore.dto.RegisterDto;
import inc.side.bookstore.response.AuthenticationResponse;
import inc.side.bookstore.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterDto register) {
        return ResponseEntity.ok(authService.registerUser(register));
    }

    @PostMapping("/authenticate")
    public  ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticateDto authDto) {
        return ResponseEntity.ok(authService.authenticateUser(authDto));
    }
}
