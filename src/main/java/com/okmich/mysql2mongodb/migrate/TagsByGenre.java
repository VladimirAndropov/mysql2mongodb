package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagsByGenre extends BaseMigration{
    /**
     *
     * @param dbServerUrl
     * @param dbUser
     * @param dbPassword
     * @param mongoDbUrl
     * @param mongoDbName
     */
    public TagsByGenre(String dbServerUrl, String dbUser, String dbPassword,
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
    public  String getDataFromMongo(String selectedItem, String selectedItem2) {
        MongoDatabase mongoDB = getMongoDatabase(mongoDbUrl, mongoDbName);
        MongoCollection<Document> collection = mongoDB.getCollection("tags");
        StringBuilder result = new StringBuilder();
        AggregateIterable<Document> documents = collection.aggregate(
                Arrays.asList(
                        new Document("$match",
                                new Document("tag", selectedItem)
                        ),
                        new Document("$unwind", "$movie"),
                        new Document("$project",
                                new Document("movie", "$movie.title")
                                        .append("_id", 0)
                        )
                )

        );

        // Обрабатываем результаты
        for (Document doc : documents) {
            result.append(doc.getString("movie")).append("\n"); // Добавляем каждый документ в виде JSON
        }

        return result.toString();
    }
}
