package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.dto.admin.AdminMessageResponseDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogResponseDto;

public interface AdminResponseHandler {
    public AdminMessageResponseDto handleAdminResponse(ChatLogResponseDto chatLogResponseDto, String message);
}
