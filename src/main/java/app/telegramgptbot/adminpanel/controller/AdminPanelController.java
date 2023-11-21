package app.telegramgptbot.adminpanel.controller;

import app.telegramgptbot.adminpanel.dto.response.ChatLogResponseDTO;
import app.telegramgptbot.adminpanel.model.ChatLog;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import app.telegramgptbot.adminpanel.service.mapper.ResponseDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel")
public class AdminPanelController {
    private final ChatLogService chatLogService;
    private final ResponseDtoMapper<ChatLogResponseDTO, ChatLog> chatLogResponseDtoMapper;

    public AdminPanelController(ChatLogService chatLogService, ResponseDtoMapper
            <ChatLogResponseDTO, ChatLog> responseDtoMapper) {
        this.chatLogService = chatLogService;
        this.chatLogResponseDtoMapper = responseDtoMapper;
    }

    @GetMapping
        public List<ChatLogResponseDTO> getAllChatLogs() {
            return chatLogService.getAll()
                    .stream().map(chatLogResponseDtoMapper::mapToDto)
                    .toList();
    }

    @PostMapping("/send-message/{chatId}")
    public void respondToUser(@PathVariable Long chatId, String message) {
        ChatLog chatLog = chatLogService.getByChatId(chatId);
        chatLog.setAdminResponse(message);
        chatLogService.update(chatLog);
    }
}
