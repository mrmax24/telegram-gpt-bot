package app.telegramgptbot.adminpanel.dao;

import app.telegramgptbot.adminpanel.model.ChatLog;

import java.util.List;
import java.util.Optional;

public interface ChatLogDao {
    ChatLog add(ChatLog chatLog);

    Optional<ChatLog> get(Long id);

    List<Object[]> findAllChats();

    List<Object[]> findByChatId(Long chatId);

    ChatLog update(ChatLog chatLog);
}
