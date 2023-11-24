package app.telegramgptbot.adminpanel.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatLogResponseDTO {
    private Long chatId;
    private String userMessage;
    private String chatResponse;
    private String adminResponse;
}
