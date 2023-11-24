package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.model.ChatLog;

import java.util.List;

public interface ChatLogService {
    ChatLog add(ChatLog chatLog);

    ChatLog get(Long id);

    List<Object[]> findAllChats();

    List<Object[]> findByChatId(Long chatId);

    ChatLog update(ChatLog chatLog);
}
