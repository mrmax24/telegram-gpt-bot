package app.telegramgptbot.telegrambot.exception;

public class UpdateProcessingException extends RuntimeException {
    public UpdateProcessingException(String message) {
        super(message);
    }
}
