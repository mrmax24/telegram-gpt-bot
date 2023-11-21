package app.telegramgptbot.adminpanel.service.mapper;

import app.telegramgptbot.adminpanel.dto.request.AdminMessageRequestDto;
import app.telegramgptbot.adminpanel.model.ChatLog;
import org.springframework.stereotype.Component;

@Component
public class AdminMessageMapper implements RequestDtoMapper<AdminMessageRequestDto, ChatLog> {
    @Override
    public ChatLog mapToModel(AdminMessageRequestDto dto) {
        ChatLog chatLog = new ChatLog();
        chatLog.setAdminResponse(dto.getAdminResponse());
        return chatLog;
    }
}
