package com.lmaestre.bot_finanzas.config;

import com.lmaestre.bot_finanzas.controllers.BotController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramConfig extends TelegramLongPollingBot {


    private final BotController botController;

    @Autowired
    public TelegramConfig(BotController botController) {
        this.botController = botController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String message = update.getMessage().getText();

        System.out.println(message);
        botController.processMessage(chatId, message)
                .doOnNext(response -> sendMessage(chatId, response))
                .subscribe();
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_USERNAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(chatId.toString(), text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
