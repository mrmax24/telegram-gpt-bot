package app.telegramgptbot.adminpanel.dto.admin;

import app.telegramgptbot.adminpanel.validation.Password;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class AdminLoginRequestDto {
    @Email
    private String email;

    @Password
    private String password;
}
