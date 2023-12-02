package app.telegramgptbot.adminpanel.service.impl;

import app.telegramgptbot.adminpanel.dto.admin.AdminMessageResponseDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogRequestDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogResponseDto;
import app.telegramgptbot.adminpanel.mapper.ChatLogMapper;
import app.telegramgptbot.adminpanel.service.AdminResponseHandler;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import app.telegramgptbot.telegrambot.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AdminResponseHandlerImpl implements AdminResponseHandler {
    private final ChatLogService chatLogService;
    private final ChatLogMapper chatLogMapper;
    private final TelegramBot telegramBot;

    public AdminMessageResponseDto handleAdminResponse(ChatLogResponseDto chatLogResponseDto, String message) {
        ChatLogRequestDto chatLogRequestDto = chatLogMapper.mapToRequestDto(chatLogResponseDto);
        long adminResponseTime = System.currentTimeMillis();
        chatLogRequestDto.setAdminResponse(message);
        chatLogRequestDto.setAdminResponseTime(new Timestamp(adminResponseTime).toLocalDateTime());
        chatLogService.save(chatLogRequestDto);
        telegramBot.sendMessage(chatLogRequestDto.getChatId(), message);
        AdminMessageResponseDto responseDto = new AdminMessageResponseDto();
        responseDto.setMessage("Message was sent successfully");
        return responseDto;
    }
}
