package com.example.dao;

import com.example.model.Mortgage;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MortgageDao {
    private MongoDatabase mongoDatabase;

    public MortgageDao(String databaseName) {
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        this.mongoDatabase = new MongoClient(connectionString).getDatabase(databaseName);
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    // Method to save mortgage data in the mongodb
    public void saveMortgageResult(Mortgage mortgage) {

        MongoCollection<Document> collection = mongoDatabase.getCollection("mortgages");

        Document document = new Document("name",mortgage.getName())
                .append("loanAmount",mortgage.getLoanAmount())
                .append("interestRate",mortgage.getInterestRate())
                .append("years",mortgage.getYears())
                .append("monthlyPayment",mortgage.calculate());
        collection.insertOne(document);
    }

    public long getCollectionCount(){
        return mongoDatabase.getCollection("mortgages").countDocuments();
    }
}
