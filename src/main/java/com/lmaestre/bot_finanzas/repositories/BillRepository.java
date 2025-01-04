package com.lmaestre.bot_finanzas.repositories;

import com.lmaestre.bot_finanzas.models.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BillRepository extends ReactiveMongoRepository<Bill, String> {
}
