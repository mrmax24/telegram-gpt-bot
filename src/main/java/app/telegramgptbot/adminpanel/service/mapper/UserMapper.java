package app.telegramgptbot.adminpanel.service.mapper;

import app.telegramgptbot.adminpanel.dto.response.UserResponseDto;
import app.telegramgptbot.adminpanel.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }
}