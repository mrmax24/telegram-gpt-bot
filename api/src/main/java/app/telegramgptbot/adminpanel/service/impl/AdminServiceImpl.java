package app.telegramgptbot.adminpanel.service.impl;

import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterRequestDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminRegisterResponseDto;
import app.telegramgptbot.adminpanel.exception.EntityNotFoundException;
import app.telegramgptbot.adminpanel.mapper.RegisterMapper;
import app.telegramgptbot.adminpanel.model.Admin;
import app.telegramgptbot.adminpanel.repository.AdminRepository;
import app.telegramgptbot.adminpanel.service.AdminService;
import app.telegramgptbot.adminpanel.exception.RegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final RegisterMapper registerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminRegisterResponseDto register(AdminRegisterRequestDto requestDto) {
        if (adminRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("This email is already in use");
        }
        Admin admin = registerMapper.mapToModel(requestDto);
        admin.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Admin savedAdmin = adminRepository.save(admin);
        return registerMapper.mapToDto(savedAdmin);
    }

    @Override
    public Admin getAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return adminRepository.findByEmail(authentication.getName()).orElseThrow(() ->
                new EntityNotFoundException("can't find user by username: "
                        + authentication.getName()));
    }
}