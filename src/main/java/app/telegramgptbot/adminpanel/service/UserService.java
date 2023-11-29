package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterResponseDto;

public interface UserService {
    AdminRegisterResponseDto register(AdminRegisterRequestDto requestDto);
}
