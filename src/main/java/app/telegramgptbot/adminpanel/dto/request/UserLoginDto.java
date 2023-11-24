package app.telegramgptbot.adminpanel.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLoginDto {
    @NotBlank(message = "Login can't be empty!")
    private String email;
    @NotBlank(message = "Password can't be empty!")
    private String password;
}
