package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.Arrays;

public class UserCountAge extends BaseMigration{

    /**
     * @param dbServerUrl
     * @param dbUser
     * @param dbPassword
     * @param mongoDbUrl
     * @param mongoDbName
     */
    public UserCountAge(String dbServerUrl, String dbUser, String dbPassword, String mongoDbUrl, String mongoDbName) {
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
        MongoCollection<Document> collection = mongoDB.getCollection("users");
        StringBuilder result = new StringBuilder();
        AggregateIterable<Document> documents = collection.aggregate(
                Arrays.asList(new Document("$group",
                                new Document("_id",
                                        new Document("age_group", "$age_group"))
                                        .append("value",
                                                new Document("$sum", 1))
                        )
                ));
        // Обрабатываем результаты
        for (Document doc : documents) {
//            result.append(doc.toJson()).append("\n"); // Добавляем каждый документ в виде JSON
            String age_group = doc.get("_id", Document.class).getString("age_group");
            Integer count = doc.getInteger("value");
            // Append age_group
            result.append(age_group).append("\t");
            // Append count and newline
            result.append(count).append("\n");
        }
        return result.toString();
    }
}
