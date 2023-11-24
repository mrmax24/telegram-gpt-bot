package app.telegramgptbot.adminpanel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Entity
@Table(name = "chat_logs")
public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "tg_username")
    private String tgUsername;

    @Column(name = "full_name")
    private String fullUsername;

    @Column(name = "user_message")
    private String userMessage;

    @Column(name = "chatgpt_response", columnDefinition = "TEXT")
    private String chatResponse;

    @Column(name = "admin_response")
    private String adminResponse;

    @Column(name = "user_message_time")
    private Timestamp userMessageTime;

    @Column(name = "chat_message_time")
    private Timestamp chatResponseTime;

    @Column(name = "admin_message_time")
    private Timestamp adminResponseTime;


    public ChatLog() {
    }

    public ChatLog(Long chatId, String tgUsername, String fullUsername, String userMessage, String chatResponse,
                   Timestamp userMessageTime, Timestamp chatResponseTime) {
        this.chatId = chatId;
        this.tgUsername = tgUsername;
        this.fullUsername = fullUsername;
        this.userMessage = userMessage;
        this.chatResponse = chatResponse;
        this.userMessageTime = userMessageTime;
        this.chatResponseTime = chatResponseTime;
    }
}