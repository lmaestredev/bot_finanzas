package com.lmaestre.bot_finanzas.services;


import com.lmaestre.bot_finanzas.models.Bill;
import com.lmaestre.bot_finanzas.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Mono<Bill> saveTransaction(Bill bill) {
        return billRepository.save(bill);
    }
    public Flux<Bill> getAllTransactions() {
        return billRepository.findAll();
    }

    public Mono<Double> calculateBalance() {
        return billRepository.findAll()
                .map(Bill::getAmount) // Extract amount
                .reduce(0.0, Double::sum); // Sum all amounts reactively
    }
}
