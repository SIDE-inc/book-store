package inc.side.bookstore.services;

import inc.side.bookstore.dto.AuthenticateDto;
import inc.side.bookstore.dto.RegisterDto;
import inc.side.bookstore.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse registerUser(RegisterDto userDto);
    AuthenticationResponse authenticateUser(AuthenticateDto authenticateDto);
}
