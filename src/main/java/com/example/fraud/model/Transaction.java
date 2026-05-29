package com.example.fraud.model;



import com.example.fraud.enums.Category;
import com.example.fraud.enums.Currency;
import com.example.fraud.enums.Merchant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import static com.example.fraud.enums.Merchant.getRandomMerchant;


@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    @JsonProperty("transaction_id")
    private String transactionId;
    private String userId;
    private double amount;
    private Currency currency;
    private Instant timestamp;
    private Merchant merchant;
    private Category category;
    private boolean isFraud;
    private float[] embedding = {};

    public Transaction(Object o, String string, String userId, double amount, Currency currency, Instant now, Merchant merchant, Category category, boolean b) {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isFraud() {
        return isFraud;
    }

    public void setFraud(boolean fraud) {
        isFraud = fraud;
    }

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

    public String generateEmbeddingText() {
        return userId + " " + amount + " " + currency + " " + merchant + " " + category;
    }
    public static Transaction generateRandom(Customer customer) {
        Random random = new Random();

        // Generate a normal or suspicious transaction
        boolean isSuspicious = random.nextDouble() < 0.1; // 10% chance of fraud

        double amount = isSuspicious ? customer.getMeanSpending() * (2 + random.nextDouble()) // Unusually large
                : customer.getMeanSpending() * (0.5 + random.nextDouble());

        Category category = isSuspicious ? customer.getUnfrequentCategory() : customer.getFrequentCategory();

        Merchant merchant = getRandomMerchant(category);

        Currency currency = isSuspicious ? customer.getRandomSuspiciousCurrency()
                : customer.getPreferredCurrency();

        return new Transaction(
                null,
                UUID.randomUUID().toString(),
                customer.getUserId(),
                amount,
                currency,
                Instant.now(),
                merchant,
                category,
                false
        );
    }
}
