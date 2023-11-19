package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.adminpanel.model.User;

public interface AuthenticationService {
    User register(String login, String email, String password);
}
