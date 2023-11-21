package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.model.User;

import java.util.Optional;

public interface UserService {
    User add(User user);

    User get(Long id);

    Optional<User> findByEmail(String email);
}
