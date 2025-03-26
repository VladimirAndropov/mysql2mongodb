package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

public class RatingsByGenre extends BaseMigration{

    public RatingsByGenre(String dbServerUrl,
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
                Arrays.asList(
                        new Document("$match", new Document("rating", Integer.parseInt(selectedItem))),
                        new Document("$lookup",
                                new Document("from", "movies")
                                        .append("localField", "movie_id")
                                        .append("foreignField", "_id")
                                        .append("as", "movie")
                        ),
                        new Document("$unwind", "$movie")
//                        new Document("$project",
//                                new Document("movie", "$movie")
//                                        .append("_id", 0)
//                                        .append("movie._id", 0)
//                                        .append("movie.release_year", 0)
//                                        .append("movie.genres", 0)
//                        )
                )
        );

        // Обрабатываем результаты
        for (Document doc : documents) {
            result.append(doc.toJson()).append("\n"); // Добавляем каждый документ в виде JSON
        }

        return result.toString();
    }
}
