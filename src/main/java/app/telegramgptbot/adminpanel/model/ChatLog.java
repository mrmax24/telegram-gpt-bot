package app.telegramgptbot.adminpanel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "chat_logs")
public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_message")
    private String userMessage;

    @Column(name = "chatgpt_response", columnDefinition = "TEXT")
    private String chatResponse;

    @Column(name = "admin_response")
    private String adminResponse;

    public ChatLog() {
    }

    public ChatLog(Long chatId, String message,
                   String response) {
        this.chatId = chatId;
        this.userMessage = message;
        this.chatResponse = response;
    }
}