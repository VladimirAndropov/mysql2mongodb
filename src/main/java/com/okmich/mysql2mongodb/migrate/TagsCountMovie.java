package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class TagsCountMovie extends BaseMigration{
    /**
     *
     * @param dbServerUrl
     * @param dbUser
     * @param dbPassword
     * @param mongoDbUrl
     * @param mongoDbName
     */
    public TagsCountMovie(String dbServerUrl, String dbUser, String dbPassword,
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
                        new Document("$group",
                                new Document("_id",
                                        new Document("tag", "$tag"))
                                        .append("value",
                                                new Document("$sum", 1))
                        )
                )

        );

        // Обрабатываем результаты
        for (Document doc : documents) {
            String genre = doc.get("_id", Document.class).getString("tag");
            int count = doc.getInteger("value");
            // Append genre
            result.append(genre).append("\t");
            // Append count and newline
            result.append(count).append("\n");
        }

        return result.toString();
    }
}
