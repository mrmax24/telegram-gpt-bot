package app.telegramgptbot.adminpanel.security;

import app.telegramgptbot.adminpanel.repository.AdminRepository;
import app.telegramgptbot.adminpanel.exception.RegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByEmail(username).orElseThrow(() ->
                new RegistrationException("can't find user by username" + username));
    }
}