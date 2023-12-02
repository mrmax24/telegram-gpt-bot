package app.telegramgptbot.adminpanel.dto.chatlog;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatLogRequestDto {
    private Long chatId;
    private String tgUsername;
    private String fullUsername;
    private String userMessage;
    private String chatResponse;
    private String adminResponse;
    private LocalDateTime userMessageTime;
    private LocalDateTime  chatResponseTime;
    private LocalDateTime adminResponseTime;

    public ChatLogRequestDto(Long chatId, String tgUsername,
                             String fullUsername, String userMessage,
                             String chatResponse, LocalDateTime userMessageTime,
                             LocalDateTime chatResponseTime) {
        this.chatId = chatId;
        this.tgUsername = tgUsername;
        this.fullUsername = fullUsername;
        this.userMessage = userMessage;
        this.chatResponse = chatResponse;
        this.userMessageTime = userMessageTime;
        this.chatResponseTime = chatResponseTime;
    }
}
