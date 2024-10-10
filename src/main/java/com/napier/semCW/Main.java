package com.napier.semCW;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Main
{
    public static void main(String[] args)
    {
        // Connect to MongoDB on local system using the host "mongo-dbserver" and default port 27000
        // MongoClient is the entry point to a MongoDB cluster
        MongoClient mongoClient = new MongoClient("mongo-dbserver");
        // Get a database - will create when we use it
        MongoDatabase database = mongoClient.getDatabase("mydb");
        // Get a collection from the database
        //Collections in MongoDB are similar to tables in SQL databases
        MongoCollection<Document> collection = database.getCollection("test");
        // Create a document to store a name, class, year and a nested document for coursework (CW) and exam (EX) results
        Document doc = new Document("name", "Kevin Sim")
                .append("class", "Software Engineering Methods")
                .append("year", "2021")
                .append("result", new Document("CW", 95).append("EX", 85));
        // Insert the document into the "test" collection
        collection.insertOne(doc);

        // Retrieve the first document from the "test" collection and print it as a JSON string
        //This is just to check if the document was successfully inserted
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }
}
