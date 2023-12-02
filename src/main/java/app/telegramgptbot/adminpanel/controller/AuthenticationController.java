package app.telegramgptbot.adminpanel.controller;

import app.telegramgptbot.adminpanel.dto.admin.AdminLoginRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminLoginResponseDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterResponseDto;
import app.telegramgptbot.adminpanel.exception.AuthenticationException;
import app.telegramgptbot.adminpanel.security.AuthenticationService;
import app.telegramgptbot.adminpanel.service.UserService;
import app.telegramgptbot.adminpanel.exception.RegistrationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AdminLoginResponseDto login(@RequestBody @Valid AdminLoginRequestDto requestDto)
            throws AuthenticationException {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminRegisterResponseDto register(@RequestBody @Valid AdminRegisterRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
