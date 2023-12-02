package app.telegramgptbot.adminpanel.mapper;

import app.telegramgptbot.adminpanel.config.MapperConfig;
import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterResponseDto;
import app.telegramgptbot.adminpanel.model.Admin;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RegisterMapper {
    Admin mapToModel(AdminRegisterRequestDto dto);

    AdminRegisterResponseDto mapToDto(Admin admin);
}
