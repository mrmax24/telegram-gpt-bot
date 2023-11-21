package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.model.ChatLog;

import java.util.List;
import java.util.Optional;

public interface ChatLogService {
    ChatLog add(ChatLog chatLog);

    List<ChatLog> getAll();

    ChatLog get(Long id);

    List<ChatLog> findByChatId(Long chatId);

    ChatLog update(ChatLog chatLog);
}
