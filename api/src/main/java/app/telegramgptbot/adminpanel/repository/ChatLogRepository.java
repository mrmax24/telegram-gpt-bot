package app.telegramgptbot.adminpanel.repository;

import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogByIdDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogListDto;
import app.telegramgptbot.adminpanel.model.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {

    @Query("SELECT NEW app.telegramgptbot.adminpanel.dto.chatlog.ChatLogListDto("
            + "c.chatId, c.tgUsername, c.userMessage, "
            + "(SELECT COUNT(c1.userMessage) FROM ChatLog c1 WHERE c1.chatId = c.chatId), "
            + "(SELECT COUNT(c2.chatResponse) FROM ChatLog c2 WHERE c2.chatId = c.chatId), "
            + "(SELECT COUNT(c3.adminResponse) FROM ChatLog c3 WHERE c3.chatId = c.chatId), "
            + "MAX(c.userMessageTime))"
            + "FROM ChatLog c "
            + "WHERE (c.chatId, c.userMessageTime) IN ("
            + "   SELECT c1.chatId, MAX(c1.userMessageTime) "
            + "   FROM ChatLog c1 "
            + "   GROUP BY c1.chatId"
            + ") "
            + "GROUP BY c.chatId, c.tgUsername, c.userMessage "
            + "ORDER BY MAX(c.userMessageTime) DESC")
    List<ChatLogListDto> getChatList();


    @Query("SELECT new app.telegramgptbot.adminpanel.dto.chatlog.ChatLogByIdDto("
            + "c.id, c.fullUsername, c.userMessageTime, c.userMessage, "
            + "c.chatResponseTime, c.chatResponse, "
            + "c.adminResponseTime, c.adminResponse) "
            + "FROM ChatLog c "
            + "WHERE c.chatId = :chatId")
    List<ChatLogByIdDto> getLogsByChatId(Long chatId);
}
