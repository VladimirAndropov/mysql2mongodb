package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

public class UserByAge extends BaseMigration{

    /**
     * @param dbServerUrl
     * @param dbUser
     * @param dbPassword
     * @param mongoDbUrl
     * @param mongoDbName
     */
    public UserByAge(String dbServerUrl, String dbUser, String dbPassword, String mongoDbUrl, String mongoDbName) {
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
        StringBuilder result = new StringBuilder();
        MongoDatabase mongoDB = getMongoDatabase(mongoDbUrl, mongoDbName);
        MongoCollection<Document> collection = mongoDB.getCollection("users");
        FindIterable<Document> documents = collection.find( Filters.gt("age_id", 18))
                .sort(Sorts.descending("age_group")) ;


        for (Document doc : documents) {
            result.append(doc.toJson()).append("\n"); // Добавляем каждый документ в виде JSON
        }
        return result.toString();
    }
}
