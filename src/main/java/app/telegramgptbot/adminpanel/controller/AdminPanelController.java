package app.telegramgptbot.adminpanel.controller;

import app.telegramgptbot.adminpanel.dto.request.AdminMessageRequestDto;
import app.telegramgptbot.adminpanel.dto.response.ChatLogResponseDTO;
import app.telegramgptbot.adminpanel.model.ChatLog;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import app.telegramgptbot.adminpanel.service.mapper.RequestDtoMapper;
import app.telegramgptbot.adminpanel.service.mapper.ResponseDtoMapper;
import app.telegramgptbot.telegrambot.TelegramBot;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-panel")
public class AdminPanelController {
    private final ChatLogService chatLogService;
    private final TelegramBot telegramBot;
    private final ResponseDtoMapper<ChatLogResponseDTO, ChatLog> chatLogResponseDtoMapper;
    private final RequestDtoMapper<AdminMessageRequestDto, ChatLog> adminMessageRequestDtoMapper;


    public AdminPanelController(ChatLogService chatLogService, TelegramBot telegramBot, ResponseDtoMapper
            <ChatLogResponseDTO, ChatLog> responseDtoMapper,
                                RequestDtoMapper<AdminMessageRequestDto, ChatLog> adminMessageRequestDtoMapper) {
        this.chatLogService = chatLogService;
        this.telegramBot = telegramBot;
        this.chatLogResponseDtoMapper = responseDtoMapper;
        this.adminMessageRequestDtoMapper = adminMessageRequestDtoMapper;
    }

    @GetMapping
        public List<ChatLogResponseDTO> getAllChatLogs() {
            return chatLogService.getAll()
                    .stream().map(chatLogResponseDtoMapper::mapToDto)
                    .toList();
    }

    @PostMapping("/send-message/{id}")
    public ChatLogResponseDTO respondToUser(@PathVariable Long id,
                                            @RequestBody AdminMessageRequestDto requestDto) {
        ChatLog chatLog = chatLogService.get(id);
        ChatLog chatLogModelWIthResponse = adminMessageRequestDtoMapper.mapToModel(requestDto);
        chatLog.setAdminResponse(chatLogModelWIthResponse.getAdminResponse());
        chatLogService.update(chatLog);
        Long userChatId = chatLog.getChatId();
        telegramBot.sendMessage(userChatId, chatLog.getAdminResponse());
        return chatLogResponseDtoMapper.mapToDto(chatLog);
    }
}
