package app.telegramgptbot.adminpanel.controller;

import app.telegramgptbot.adminpanel.dto.request.UserRequestDto;
import app.telegramgptbot.adminpanel.dto.response.UserResponseDto;
import app.telegramgptbot.adminpanel.model.User;
import app.telegramgptbot.adminpanel.service.AuthenticationService;
import app.telegramgptbot.adminpanel.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final ResponseDtoMapper<UserResponseDto, User> userDtoResponseMapper;

    public AuthenticationController(AuthenticationService authService,
                                    ResponseDtoMapper<UserResponseDto, User> userDtoResponseMapper) {
        this.authService = authService;
        this.userDtoResponseMapper = userDtoResponseMapper;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto) {
        User user = authService.register(requestDto.getLogin(),
                requestDto.getEmail(), requestDto.getPassword());
        return userDtoResponseMapper.mapToDto(user);
    }
}
