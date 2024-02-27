package com.lmaestre.bot_finanzas.drivenAdapters.outbound;

import com.mongodb.ConnectionString;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.stereotype.Component;

@Component
public class ReactiveMongoDBConnectionManager {

    private static final String MONGO_URI = System.getenv("mongoUri");
    private static final String DATABASE_NAME = System.getenv("databaseName"); // Reemplaza con el nombre de tu base de datos

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ReactiveMongoDBConnectionManager(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public ReactiveMongoTemplate reactiveMongoTemplate() {

        int maxRetries = 3;
        int currentRetry = 0;

        while (currentRetry < maxRetries) {
            try {
                System.out.println("------------------------------");
                System.out.println(MONGO_URI);
                System.out.println(DATABASE_NAME);
                ReactiveMongoDatabaseFactory databaseFactory = new SimpleReactiveMongoDatabaseFactory((MongoClient) new ConnectionString(MONGO_URI), DATABASE_NAME);
                return new ReactiveMongoTemplate(databaseFactory);
            } catch (MongoSocketOpenException | MongoTimeoutException e) {
                // Manejar la excepción y esperar antes de intentar nuevamente
                System.out.println("Error de conexión: " + e.getMessage());
                waitBeforeRetry();
                currentRetry++;
            }
        }

        throw new RuntimeException("No se pudo conectar a MongoDB después de varios intentos.");
    }

    private void waitBeforeRetry() {
        try {
            // Esperar 5 segundos antes de intentar nuevamente
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

//    public Mono<String> performReactiveMongoOperation() {
//        // Ejemplo de operación reactiva con MongoDB
//        return reactiveMongoTemplate.getCollection("yourCollectionName") // Reemplaza con el nombre de tu colección
//                .flatMap(collection -> collection.find().limit(1).first())
//                .map(document -> {
//                    // Puedes realizar alguna lógica adicional si es necesario
//                    return "Operación reactiva realizada exitosamente";
//                });
//    }
}
