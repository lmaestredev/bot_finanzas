package com.lmaestre.bot_finanzas.telegram;

import com.lmaestre.bot_finanzas.config.TelegramConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBotConnection implements CommandLineRunner {

    private final TelegramConfig telegramConfig;

    @Autowired
    public TelegramBotConnection(TelegramConfig telegramConfig) {
        this.telegramConfig = telegramConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramConfig);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
