package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;

public class UsersByGenre extends BaseMigration {
    /**
     *
     * @param dbServerUrl
     * @param dbUser
     * @param dbPassword
     * @param mongoDbUrl
     * @param mongoDbName
     */
    public UsersByGenre(String dbServerUrl,
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

    public String getDataFromMongo(String selectedItem, String selectedItem2) {

        MongoDatabase mongoDB = getMongoDatabase(mongoDbUrl, mongoDbName);
        MongoCollection<Document> collection = mongoDB.getCollection("ratings");
        StringBuilder result = new StringBuilder();

        AggregateIterable<Document> documents = collection.aggregate(
                Arrays.asList(
                        new Document("$match",
                                new Document("rating", 5)
                        ),
                        new Document("$lookup",
                                new Document("from", "users")
                                        .append("localField", "user_id")
                                        .append("foreignField", "_id")
                                        .append("as", "user")
                        ),
                        new Document("$unwind", "$user"),
                        new Document("$project",
                                new Document("zip_code", "$user.zip_code")
                                        .append("gender", "$user.gender")
                                        .append("age_group", "$user.age_group")
                        ),
                        new Document("$match",
                                new Document("gender", selectedItem)
                                        .append("age_group", selectedItem2)
                        )
                )
        );

        // Обрабатываем результаты
        for (Document doc : documents) {
            result.append(doc.toJson()).append("\n"); // Добавляем каждый документ в виде JSON
        }

        return result.toString();
    }
}
