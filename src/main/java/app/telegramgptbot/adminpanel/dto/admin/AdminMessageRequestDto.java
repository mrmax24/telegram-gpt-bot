package app.telegramgptbot.adminpanel.dto.admin;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class AdminMessageRequestDto {
    @NotEmpty
    String adminResponse;
}
