package app.telegramgptbot.telegrambot.exception;

public class OpenAiResponseException extends RuntimeException {
    public OpenAiResponseException(String message) {
        super(message);
    }
}
