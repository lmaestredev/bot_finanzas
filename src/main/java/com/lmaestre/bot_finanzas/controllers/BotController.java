package com.lmaestre.bot_finanzas.controllers;

import com.lmaestre.bot_finanzas.models.Bill;
import com.lmaestre.bot_finanzas.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Controller
public class BotController {

    @Autowired
    private BillService billService;

    public Mono<String> processMessage(Long chatId, String message) {

        if (!isUserAuthorized(chatId)) {
            return Mono.just("⛔ No estás autorizado para usar este bot. Contacta al administrador.");
        }

        if (message.startsWith("/start")) {
            return Mono.just("¡Bienvenido al bot de Finanzas Personales! 🎉\n\n" +
                    "Estos son algunos comandos que puedes usar:\n\n" +
                    "1 - /add_income <monto> <payType> -> Agrega un ingreso\n\n" +
                    "2 - /add_expense <monto> <payType> -> Agrega un gasto\n\n" +
                    "3 - /report -> Muestra un resumen de tus finanzas");
        } else if (message.startsWith("/add_income")) {
            return handleAddTransaction(message, true);
        } else if (message.startsWith("/add_expense")) {
            return handleAddTransaction(message, false);
        } else if (message.equals("/report")) {
            return handleReport();
        } else {
            return Mono.just("Comando no reconocido. Usa /start para ver los comandos disponibles.");
        }
    }

    private boolean isUserAuthorized(Long chatId) {
        String authorizedUserId = System.getenv("BOT_USERNAME_ID");
        return chatId.toString().equals(authorizedUserId);
    }

    private Mono<String> handleAddTransaction(String message, boolean isIncome) {

        String[] parts = message.split(" ");

        if (parts.length < 3) {
            return Mono.just("Por favor, proporciona información válida. Ejemplo: " +
                    (isIncome ? "/add_income 500 cash" : "/add_expense 300 credit_card visa"));
        }

        try {
            double amount = Double.parseDouble(parts[1]);
            String payType = parts[2];
            String cardType = parts.length > 3 ? parts[3] : "N/A";

            if (!isIncome) {
                amount = -amount;
            }

            Bill bill = new Bill();
            bill.setAmount(amount);
            bill.setPayType(payType);
            bill.setCardType(cardType);
            bill.setDate(LocalDate.now());

            return billService.saveTransaction(bill)
                    .map(saved -> "💾 Transacción guardada correctamente: " +
                            (isIncome ? "Ingreso" : "Gasto") +
                            " de $" + Math.abs(saved.getAmount()) + ", usando " + saved.getPayType() + " - " + saved.getCardType());

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return Mono.just("El monto proporcionado no es válido. Por favor, usa un número. Ejemplo: /add_income 500 cash");
        }
    }


    private Mono<String> handleReport() {
        return billService.calculateBalance()
                .map(balance -> "📊 Resumen de tus Finanzas:\n" +
                        "💰 Balance total: $" + balance);
    }
}
