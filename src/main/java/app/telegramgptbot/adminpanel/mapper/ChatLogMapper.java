package app.telegramgptbot.adminpanel.mapper;

import app.telegramgptbot.adminpanel.config.MapperConfig;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogRequestDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogResponseDto;
import app.telegramgptbot.adminpanel.model.ChatLog;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ChatLogMapper {
    ChatLogResponseDto mapToDto(ChatLog chatLog);

    ChatLog mapToModel(ChatLogRequestDto chatLogRequestDto);

    ChatLogRequestDto mapToRequestDto(ChatLogResponseDto chatLogResponseDto);
}