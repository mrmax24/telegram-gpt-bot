package app.telegramgptbot.adminpanel.service;

import app.telegramgptbot.exception.AuthenticationException;
import app.telegramgptbot.adminpanel.model.User;

public interface AuthenticationService {
    User register(String login, String email, String password);

    User login(String login, String password) throws AuthenticationException;
}
