package app.telegramgptbot.telegrambot.exception;

public class ChatActionProcessingException extends RuntimeException {
    public ChatActionProcessingException(String message) {
        super(message);
    }
}