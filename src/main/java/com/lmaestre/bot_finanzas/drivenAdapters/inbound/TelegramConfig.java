package com.lmaestre.bot_finanzas.drivenAdapters.inbound;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramConfig extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        Long chatId = update.getMessage().getChatId();
        System.out.println("Mensaje recibido: " + update.getMessage().getText() + " - from: " + chatId.toString());

        //logica para validar usuario

        // logica para procesar endpoint
        sendMessage(generateSendMessage(chatId));
    }

    @Override
    public String getBotUsername() {
        return System.getenv("bot_username");
    }

    @Override
    public String getBotToken() {
        return System.getenv("bot_token");
    }

    private SendMessage generateSendMessage(Long chatId ){
        return new SendMessage(chatId.toString(), "Luis alfredo" );
    }

    private void sendMessage(SendMessage sendMessage){
        try{
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
