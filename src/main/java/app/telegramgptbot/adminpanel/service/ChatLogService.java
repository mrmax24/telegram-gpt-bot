package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.model.ChatLog;

import java.util.List;

public interface ChatLogService {
    ChatLog add(ChatLog chatLog);

    List<ChatLog> getAll();

    ChatLog get(Long id);

    List<Object[]> findMostRecentChats();

    List<Object[]> findByChatId(Long chatId);

    ChatLog update(ChatLog chatLog);
}
