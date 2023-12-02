package app.telegramgptbot.adminpanel.dto.admin;

import app.telegramgptbot.adminpanel.validation.Email;
import app.telegramgptbot.adminpanel.validation.Password;

import lombok.Getter;

@Getter
public class AdminLoginRequestDto {
    @Email
    private String email;

    @Password
    private String password;
}
