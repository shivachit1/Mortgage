package com.example.services;

import com.example.model.Customer;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CustomerService {
    private final MongoDatabase mongoDatabase;

    public CustomerService(String databaseName) {
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        this.mongoDatabase = new MongoClient(connectionString).getDatabase(databaseName);
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    // Method to save customer data in the mongodb
    public void saveCustomer(Customer customer) {

        MongoCollection<Document> collection = mongoDatabase.getCollection("customers");
        Document document = new Document("name", customer.getName())
                .append("loanAmount", customer.getLoanAmount())
                .append("interestRate", customer.getInterestRate())
                .append("years", customer.getYears())
                .append("monthlyPayment", customer.getMonthlyPayment());
        collection.insertOne(document);
    }

    public long getCollectionCount(){
        return mongoDatabase.getCollection("customers").countDocuments();
    }
}
