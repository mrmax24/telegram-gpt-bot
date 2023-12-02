package app.telegramgptbot.adminpanel.mapper;

import app.telegramgptbot.adminpanel.config.MapperConfig;
import app.telegramgptbot.adminpanel.dto.admin.AdminMessageRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminMessageResponseDto;
import app.telegramgptbot.adminpanel.model.ChatLog;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface AdminMessageMapper {
    ChatLog mapToModel(AdminMessageRequestDto dto);

    AdminMessageResponseDto mapToDto(ChatLog chatLog);
}
