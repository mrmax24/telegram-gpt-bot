package app.telegramgptbot.adminpanel.service.impl;

import app.telegramgptbot.adminpanel.dao.ChatLogDao;
import app.telegramgptbot.adminpanel.model.ChatLog;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import app.telegramgptbot.exception.DataProcessingException;
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
    public ChatLog get(Long id) {
        return chatLogDao.get(id).orElseThrow(
                () -> new DataProcessingException("Chat with id " + id + " not found"));
    }

    @Override
    public List<Object[]> findAllChats() {
       return chatLogDao.findAllChats();
    }

    @Override
    public List<Object[]> findByChatId(Long chatId) {
        return chatLogDao.findByChatId(chatId);
    }

    @Override
    public ChatLog update(ChatLog chatLog) {
        return chatLogDao.update(chatLog);
    }
}
