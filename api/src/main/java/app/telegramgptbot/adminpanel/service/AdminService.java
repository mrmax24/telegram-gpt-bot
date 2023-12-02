package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterResponseDto;
import app.telegramgptbot.adminpanel.model.Admin;

public interface AdminService {
    AdminRegisterResponseDto register(AdminRegisterRequestDto requestDto);

    Admin getAdmin();
}
