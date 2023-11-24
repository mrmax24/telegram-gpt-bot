package app.telegramgptbot.adminpanel.dao.impl;

import app.telegramgptbot.adminpanel.dao.AbstractDao;
import app.telegramgptbot.adminpanel.dao.ChatLogDao;
import app.telegramgptbot.exception.DataProcessingException;
import app.telegramgptbot.adminpanel.model.ChatLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatLogDaoImpl extends AbstractDao<ChatLog> implements ChatLogDao {
    public ChatLogDaoImpl(SessionFactory factory) {
        super(factory, ChatLog.class);
    }

    @Override
    public List<Object[]> findAllChats() {
        try (Session session = factory.openSession()) {
            String hql = "SELECT c.chatId, c.tgUsername, c.userMessage, "
                    + "(SELECT COUNT(c1.userMessage) FROM ChatLog c1 "
                    + "WHERE c1.chatId = c.chatId) as userMessageCount, "
                    + "(SELECT COUNT(c2.chatResponse) FROM ChatLog c2 "
                    + "WHERE c2.chatId = c.chatId) as chatResponseCount, "
                    + "(SELECT COUNT(c3.adminResponse) FROM ChatLog c3 "
                    + "WHERE c3.chatId = c.chatId) as adminResponseCount, "
                    + "MAX(c.userMessageTime) as maxUserMessageTime "
                    + "FROM ChatLog c "
                    + "WHERE (c.chatId, c.userMessageTime) IN ("
                    + "   SELECT c1.chatId, MAX(c1.userMessageTime) "
                    + "   FROM ChatLog c1 "
                    + "   GROUP BY c1.chatId"
                    + ") "
                    + "GROUP BY c.chatId, c.tgUsername, c.userMessage "
                    + "ORDER BY MAX(c.userMessageTime) DESC";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving chat logs summary. "
                    + "Reason: + " + e.getMessage());
        }
    }

    @Override
    public List<Object[]> findByChatId(Long chatId) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT c.id, c.fullUsername, c.userMessageTime, c.userMessage, "
                    + "c.chatResponseTime, c.chatResponse, "
                    + "c.adminResponseTime, c.adminResponse "
                    + "FROM ChatLog c "
                    + "WHERE c.chatId = :chatId";
            Query<Object[]> query = session.createQuery(hql, Object[].class)
                    .setParameter("chatId", chatId);
        return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving chat logs "
                    + "by chat id " + chatId + " Reason: " + e.getMessage());
        }
    }
}
