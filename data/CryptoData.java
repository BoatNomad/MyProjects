package data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.App;


public class CryptoData extends App {

    public static void dbCollecting(Double price, String currency, String date) {
        //Creating the db client
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("CryptoData");

        MongoCollection<Document> collection = database.getCollection("Crypto");

        Document document = new Document("Currency", getCurrency())
        .append("Price",getPrice()).append("Time", getDate());


        //Adding the document to db collection
        collection.insertOne(document);

        mongoClient.close();
    }

}
