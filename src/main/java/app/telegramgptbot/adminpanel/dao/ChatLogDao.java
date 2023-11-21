package app.telegramgptbot.adminpanel.dao;

import app.telegramgptbot.adminpanel.model.ChatLog;

import java.util.List;
import java.util.Optional;

public interface ChatLogDao {
    ChatLog add(ChatLog chatLog);

    List<ChatLog> getAll();

    Optional<ChatLog> getByChatId(Long chatId);

    List<ChatLog> findByChatId(Long chatId);

    ChatLog update(ChatLog chatLog);
}
