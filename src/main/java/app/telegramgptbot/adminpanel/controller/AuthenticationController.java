package app.telegramgptbot.adminpanel.controller;

import app.telegramgptbot.adminpanel.dto.request.UserLoginDto;
import app.telegramgptbot.adminpanel.dto.request.UserRequestDto;
import app.telegramgptbot.exception.AuthenticationException;
import app.telegramgptbot.adminpanel.model.User;
import app.telegramgptbot.adminpanel.security.jwt.JwtTokenProvider;
import app.telegramgptbot.adminpanel.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationService authService,
                                    JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRequestDto requestDto) {
            User user = authService.register(requestDto.getLogin(),
                    requestDto.getEmail(), requestDto.getPassword());
            String token = jwtTokenProvider.createToken(user.getLogin());
            return new ResponseEntity<>(Map.of("token", token,
                    "redirect", "/admin-panel"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto userLoginDto)
            throws AuthenticationException {
            User user = authService.login(userLoginDto.getEmail(),
                    userLoginDto.getPassword());
            String token = jwtTokenProvider.createToken(user.getEmail());
            return new ResponseEntity<>(Map.of("token", token,
                    "redirect", "/admin-panel"), HttpStatus.OK);
    }
}
