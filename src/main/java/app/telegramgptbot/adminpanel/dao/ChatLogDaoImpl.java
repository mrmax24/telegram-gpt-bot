package app.telegramgptbot.adminpanel.dao;

import app.telegramgptbot.adminpanel.model.ChatLog;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChatLogDaoImpl extends AbstractDao<ChatLog> implements ChatLogDao {
    public ChatLogDaoImpl(SessionFactory factory) {
        super(factory, ChatLog.class);
    }

    @Override
    public Optional<ChatLog> get(Long id) {
        return super.get(id);
    }

    @Override
    public List<ChatLog> findByChatId(Long chatId) {
        return null;
    }
}
