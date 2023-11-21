package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.dao.ChatLogDao;
import app.telegramgptbot.adminpanel.model.ChatLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatLogServiceImpl implements ChatLogService {
    private final ChatLogDao chatLogDao;

    public ChatLogServiceImpl(ChatLogDao chatLogDao) {
        this.chatLogDao = chatLogDao;
    }

    @Override
    public ChatLog add(ChatLog chatLog) {
        return chatLogDao.add(chatLog);
    }

    @Override
    public List<ChatLog> getAll() {
        return chatLogDao.getAll();
    }

    @Override
    public ChatLog get(Long id) {
        return chatLogDao.get(id).orElseThrow(
                () -> new RuntimeException("Chat with id " + id + " not found"));
    }

    @Override
    public List<ChatLog> findByChatId(Long chatId) {
        return chatLogDao.findByChatId(chatId);
    }

    @Override
    public ChatLog update(ChatLog chatLog) {
        return chatLogDao.update(chatLog);
    }
}
