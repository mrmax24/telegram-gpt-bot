package app.telegramgptbot.adminpanel.controller;

import app.telegramgptbot.adminpanel.dto.request.AdminMessageRequestDto;
import app.telegramgptbot.adminpanel.dto.response.ChatLogResponseDTO;
import app.telegramgptbot.adminpanel.model.ChatLog;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import app.telegramgptbot.adminpanel.service.mapper.RequestDtoMapper;
import app.telegramgptbot.adminpanel.service.mapper.ResponseDtoMapper;
import app.telegramgptbot.telegrambot.TelegramBot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/admin-panel")
public class AdminPanelController {
    private final ChatLogService chatLogService;
    private final TelegramBot telegramBot;
    private final ResponseDtoMapper<ChatLogResponseDTO, ChatLog> chatLogResponseDtoMapper;
    private final RequestDtoMapper<AdminMessageRequestDto, ChatLog> adminMessageRequestDtoMapper;


    public AdminPanelController(ChatLogService chatLogService, TelegramBot telegramBot,
                                ResponseDtoMapper<ChatLogResponseDTO, ChatLog> responseDtoMapper,
                                RequestDtoMapper<AdminMessageRequestDto,
                                        ChatLog> adminMessageRequestDtoMapper) {
        this.chatLogService = chatLogService;
        this.telegramBot = telegramBot;
        this.chatLogResponseDtoMapper = responseDtoMapper;
        this.adminMessageRequestDtoMapper = adminMessageRequestDtoMapper;
    }

    @GetMapping("/all-chats")
    public ResponseEntity<List<Object[]>> getMostRecentChats() {
        List<Object[]> mostRecentChats = chatLogService.findAllChats();
        return ResponseEntity.ok(mostRecentChats);
    }

    @GetMapping("/logs-by-chat/{chatId}")
    public ResponseEntity<List<Object[]>> getLogsByChatId(@PathVariable Long chatId) {
        List<Object[]> logsByChatId = chatLogService.findByChatId(chatId);
        return ResponseEntity.ok(logsByChatId);
    }

    @PostMapping("/send-message/{id}")
    public ChatLogResponseDTO respondToUser(@PathVariable Long id,
                                            @RequestBody AdminMessageRequestDto requestDto) {
        ChatLog chatLog = chatLogService.get(id);
        String adminResponse = adminMessageRequestDtoMapper
                .mapToModel(requestDto).getAdminResponse();
        chatLog.setAdminResponse(adminResponse);
        Long userChatId = chatLog.getChatId();
        telegramBot.sendMessage(userChatId, adminResponse);
        long adminResponseTime = System.currentTimeMillis();
        chatLog.setAdminResponseTime(new Timestamp(adminResponseTime).toLocalDateTime());
        chatLogService.update(chatLog);
        return chatLogResponseDtoMapper.mapToDto(chatLog);
    }
}
