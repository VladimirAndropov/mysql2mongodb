package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class RatingsCountMovie extends BaseMigration{

    public RatingsCountMovie(String dbServerUrl,
                             String dbUser, String dbPassword,
                             String mongoDbUrl, String mongoDbName) {
        super(dbServerUrl, dbUser, dbPassword, mongoDbUrl, mongoDbName);
    }

    @Override
    public void migrate() {

    }

    @Override
    protected Document rowToDocument(Object... row) {
        return null;
    }

    @Override
    public String getDataFromMongo(String selectedItem, String selectedItem2) {

        MongoDatabase mongoDB = getMongoDatabase(mongoDbUrl, mongoDbName);
        MongoCollection<Document> collection = mongoDB.getCollection("ratings");
        StringBuilder result = new StringBuilder();

        // Выполняем запрос (например, получаем все документы)
        AggregateIterable<Document> documents = collection.aggregate(
                Arrays.asList(new Document("$group",
                                new Document("_id",
                                        new Document("rating", "$rating"))
                                        .append("value",
                                                new Document("$sum", 1))
                        )
                ));

        // Обрабатываем результаты
        for (Document doc : documents) {
//            result.append(doc.toJson()).append("\n"); // Добавляем каждый документ в виде JSON
            Double rating = doc.get("_id", Document.class).getDouble("rating");
            int count = doc.getInteger("value");
            // Append genre
            result.append(rating).append("\t");
            // Append count and newline
            result.append(count).append("\n");

        }

        return result.toString();
    }
}
