package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogByIdDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogListDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogRequestDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogResponseDto;

import java.util.List;

public interface ChatLogService {
    ChatLogResponseDto save(ChatLogRequestDto chatLogRequestDto);

    ChatLogResponseDto getChatLogById(Long id);

    List<ChatLogListDto> getChatList();

    List<ChatLogByIdDto> getLogsByChatId(Long chatId);
}
