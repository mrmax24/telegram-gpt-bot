package app.telegramgptbot.adminpanel.service.impl;

import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterResponseDto;
import app.telegramgptbot.adminpanel.mapper.RegisterMapper;
import app.telegramgptbot.adminpanel.model.Admin;
import app.telegramgptbot.adminpanel.repository.UserRepository;
import app.telegramgptbot.adminpanel.service.UserService;
import app.telegramgptbot.adminpanel.exception.RegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RegisterMapper registerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminRegisterResponseDto register(AdminRegisterRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("This email is already in use");
        }
        Admin admin = registerMapper.mapToModel(requestDto);
        admin.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Admin savedAdmin = userRepository.save(admin);
        return registerMapper.mapToDto(savedAdmin);
    }
}
