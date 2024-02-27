package com.lmaestre.bot_finanzas.drivenAdapters.inbound;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBotConnection implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramConfig());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
