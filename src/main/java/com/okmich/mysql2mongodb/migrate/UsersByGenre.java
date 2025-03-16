package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

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
        MongoDatabase mongoDB = getMongoDatabase(mongoDbUrl, mongoDbName);
        MongoCollection collection = mongoDB.getCollection("users");
        StringBuilder result = new StringBuilder();
        // Выполняем запрос (например, получаем все документы)
        FindIterable<Document> documents = collection.find();

        // Обрабатываем результаты
        for (Document doc : documents) {
            result.append(doc.toJson()).append("\n"); // Добавляем каждый документ в виде JSON
            }

//        return result.toString();
    }

    @Override
    protected Document rowToDocument(Object... row) {
        return null;
    }
}
