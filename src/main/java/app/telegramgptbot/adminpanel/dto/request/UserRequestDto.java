package app.telegramgptbot.adminpanel.dto.request;

import app.telegramgptbot.adminpanel.validation.FieldsValueMatch;
import app.telegramgptbot.adminpanel.validation.ValidEmail;
import app.telegramgptbot.adminpanel.validation.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)

public class UserRequestDto {
    @Size(min = 6, max = 15, message =
            "Length must be between 6 and 15 characters")
    private String login;
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40, message =
            "Length must be between 8 and 40 characters")
    @ValidPassword
    private String password;
    private String repeatPassword;
}
