package app.telegramgptbot.adminpanel.mapper;

import app.telegramgptbot.adminpanel.config.MapperConfig;
import app.telegramgptbot.adminpanel.dto.admin.AdminResponseDto;
import app.telegramgptbot.adminpanel.model.Admin;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface AdminMapper {
    AdminResponseDto mapToDto(Admin admin);
}
