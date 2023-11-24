package app.telegramgptbot.adminpanel.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AdminMessageRequestDto {
    @NotBlank(message = "Message cannot be empty")
    String adminResponse;
}
