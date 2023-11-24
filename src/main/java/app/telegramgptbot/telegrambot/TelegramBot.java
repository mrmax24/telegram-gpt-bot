package app.telegramgptbot.telegrambot;

import app.telegramgptbot.exception.DataProcessingException;
import app.telegramgptbot.adminpanel.model.ChatLog;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import app.telegramgptbot.telegrambot.config.TelegramBotConfig;
import app.telegramgptbot.telegrambot.service.ChatGptService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfig telegramBotConfig;
    private final ChatGptService chatGptService;
    private final ChatLogService chatLogService;


    public TelegramBot(TelegramBotConfig telegramBotConfig,
                       ChatGptService chatGptService,
                       ChatLogService chatLogService) {
        this.telegramBotConfig = telegramBotConfig;
        this.chatGptService = chatGptService;
        this.chatLogService = chatLogService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String userMessage = message.getText();
            long userMessageReceivedTime = System.currentTimeMillis();

            Long chatId = message.getChatId();
            User user = message.getFrom();
            String userName = user.getUserName();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String fullName = firstName + " " + lastName;

            try {
                sendChatAction(chatId);
                String chatGptResponse = chatGptService.getChatGptResponse(userMessage);
                long chatGptResponseTime = System.currentTimeMillis();

                chatLogService.add(new ChatLog(chatId, userName, fullName, userMessage,
                        chatGptResponse, new Timestamp(userMessageReceivedTime).toLocalDateTime(),
                        new Timestamp(chatGptResponseTime).toLocalDateTime()));

                switch (userMessage) {
                    case "/start":
                        startCommandReceived(chatId, firstName);
                        break;
                    default:
                        sendMessage(chatId, chatGptResponse);
                        break;
                }
            } catch (Exception e) {
                throw new DataProcessingException("Error processing the received update. " +
                        " Reason: " + e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getBotToken();
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!";
        sendMessage(chatId, answer);
    }

    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new DataProcessingException("Failed to send the message."
                    + " Reason: " + e.getMessage());
        }
    }

    private void sendChatAction(long chatId) {
        SendChatAction sendChatAction = new SendChatAction();
        sendChatAction.setChatId(String.valueOf(chatId));
        sendChatAction.setAction(ActionType.TYPING);

        try {
            execute(sendChatAction);
        } catch (TelegramApiException e) {
            throw new DataProcessingException("Error sending chat action: "
                    + "Reason: " + e.getMessage());
        }
    }
}
