package app.telegramgptbot.adminpanel.service.mapper;

import app.telegramgptbot.adminpanel.dto.response.ChatLogResponseDTO;
import app.telegramgptbot.adminpanel.model.ChatLog;
import org.springframework.stereotype.Component;

@Component
public class ChatLogMapper implements ResponseDtoMapper<ChatLogResponseDTO, ChatLog>{
    @Override
    public ChatLogResponseDTO mapToDto(ChatLog chatLog) {
        ChatLogResponseDTO responseDTO = new ChatLogResponseDTO();
        responseDTO.setChatId(chatLog.getChatId());
        responseDTO.setUserMessage(chatLog.getUserMessage());
        responseDTO.setChatResponse(chatLog.getChatResponse());
        responseDTO.setAdminResponse(chatLog.getAdminResponse());
        return responseDTO;
    }
}
