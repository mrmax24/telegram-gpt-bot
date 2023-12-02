package app.telegramgptbot.adminpanel.mapper;

import app.telegramgptbot.adminpanel.config.MapperConfig;
import app.telegramgptbot.adminpanel.dto.admin.AdminLoginRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminLoginResponseDto;
import app.telegramgptbot.adminpanel.model.Admin;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface LoginMapper {
    Admin mapToModel(AdminLoginRequestDto dto);

    AdminLoginResponseDto mapToDto(Admin admin);
}
