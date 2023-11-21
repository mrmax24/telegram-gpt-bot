package app.telegramgptbot.adminpanel.dto.response;

import lombok.Data;

@Data
public class ChatLogResponseDTO {
    private Long chatId;
    private String userMessage;
    private String response;
}
