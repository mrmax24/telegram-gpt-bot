package app.telegramgptbot.adminpanel.dto.chatlog;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatLogResponseDto {
    private Long id;
    private Long chatId;
    private String tgUsername;
    private String fullUsername;
    private String userMessage;
    private String chatResponse;
    private String adminResponse;
    private LocalDateTime userMessageTime;
    private LocalDateTime  chatResponseTime;
    private LocalDateTime adminResponseTime;
}
