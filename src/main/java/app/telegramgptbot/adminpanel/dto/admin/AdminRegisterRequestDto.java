package app.telegramgptbot.adminpanel.dto.admin;

import app.telegramgptbot.adminpanel.validation.FieldsValueMatch;
import app.telegramgptbot.adminpanel.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)

public class AdminRegisterRequestDto {
    @Size(min = 6, max = 15)
    private String login;
    @Email
    private String email;
    @Size(min = 8, max = 40)
    @Password
    private String password;
    private String repeatPassword;
}
