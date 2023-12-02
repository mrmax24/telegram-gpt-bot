package app.telegramgptbot.telegrambot.command;

import app.telegramgptbot.telegrambot.TelegramBot;
import app.telegramgptbot.telegrambot.command.Command;

public class SendMessageCommand implements Command {
    private final Long chatId;
    private final String text;
    private final TelegramBot telegramBot;

    public SendMessageCommand(Long chatId, String text, TelegramBot telegramBot) {
        this.chatId = chatId;
        this.text = text;
        this.telegramBot = telegramBot;
    }

    @Override
    public void execute() {
        telegramBot.sendMessage(chatId, text);
    }
}
