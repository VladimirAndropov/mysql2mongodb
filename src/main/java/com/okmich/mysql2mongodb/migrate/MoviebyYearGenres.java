package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class MoviebyYearGenres extends BaseMigration{

    public MoviebyYearGenres(String dbServerUrl,
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
        MongoCollection<Document> collection = mongoDB.getCollection("movies");
        StringBuilder result = new StringBuilder();
        AggregateIterable<Document> documents = collection.aggregate(
                Arrays.asList(
                        new Document("$project", new Document("release_year", 1)
                                .append("genres", 1)
                                .append("title", 1)
                                .append("_id", 0)),
                        new Document("$unwind", "$genres"),
//                new Document("$group", new Document("_id", new Document("year", "$release_year")
//                        .append("genre", "$genres"))
//                        .append("value", new Document("$sum", 1))),
                        new Document("$match",
                                new Document("release_year", Integer.parseInt(selectedItem))
                                        .append("genres", selectedItem2)
                        )
                ));
        // Обрабатываем результаты
        for (Document doc : documents) {
            result.append(doc.toJson()).append("\n"); // Добавляем каждый документ в виде JSON
        }

        return result.toString();
    }
}
