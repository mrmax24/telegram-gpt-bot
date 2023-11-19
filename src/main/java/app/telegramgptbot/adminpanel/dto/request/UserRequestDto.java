package app.telegramgptbot.adminpanel.dto.request;

import app.telegramgptbot.adminpanel.lib.FieldsValueMatch;
import app.telegramgptbot.adminpanel.lib.ValidEmail;

import javax.validation.constraints.Size;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)

public class UserRequestDto {
    private String login;
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
