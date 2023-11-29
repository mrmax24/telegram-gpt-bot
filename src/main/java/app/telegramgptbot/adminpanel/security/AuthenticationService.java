package app.telegramgptbot.adminpanel.security;

import app.telegramgptbot.adminpanel.dto.admin.AdminLoginRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminLoginResponseDto;
import app.telegramgptbot.adminpanel.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AdminLoginResponseDto authenticate(AdminLoginRequestDto requestDto) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));
            String token = jwtUtil.generateToken(authentication.getName());
            return new AdminLoginResponseDto(token);
        } catch (Exception e) {
            throw new AuthenticationException("Incorrect username or password");
        }
    }
}
