package app.telegramgptbot.telegrambot;

import app.telegramgptbot.adminpanel.model.ChatLog;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import app.telegramgptbot.telegrambot.config.TelegramBotConfig;
import app.telegramgptbot.telegrambot.service.ChatGptService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
            String userMessage = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            sendChatAction(chatId, ActionType.TYPING);
            try {
                String chatGptResponse = chatGptService.getChatGptResponse(userMessage);

                chatLogService.add(new ChatLog(chatId, userMessage, chatGptResponse));

                switch (userMessage) {
                    case "/start":
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    default:
                        sendMessage(update.getMessage().getChatId(), chatGptResponse);
                        break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
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

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
        }
    }

    private void sendChatAction(long chatId, ActionType action) {
        SendChatAction sendChatAction = new SendChatAction();
        sendChatAction.setChatId(String.valueOf(chatId));
        sendChatAction.setAction(action);

        try {
            execute(sendChatAction);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Error sending chat action: " + e.getMessage(), e);
        }
    }
}
