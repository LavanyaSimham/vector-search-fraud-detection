

# 🚀 Vector Search Fraud Detection

> AI-Powered Real-Time Fraud Detection Platform using **Spring Boot**, **Apache Kafka**, **MongoDB Atlas Vector Search**, **Spring AI**, and **OpenAI Embeddings**

---

## 📖 Overview

Traditional fraud detection systems rely heavily on predefined business rules such as:

* Transaction amount exceeds a threshold
* Unusual transaction location
* Multiple transactions within a short period

While these rules help identify obvious fraud, they often fail to detect subtle behavioral anomalies.

This project leverages **Artificial Intelligence**, **Vector Embeddings**, and **MongoDB Atlas Vector Search** to analyze customer transaction behavior and identify suspicious financial activities in real time.

The system continuously processes streaming transactions, generates AI embeddings, performs vector similarity searches against historical customer behavior, and automatically flags potentially fraudulent transactions.

---

## 🎯 Project Goals

✅ Process transactions in real time

✅ Build event-driven architecture using Kafka

✅ Generate AI embeddings from transaction data

✅ Store transactions in MongoDB Atlas

✅ Perform vector similarity search

✅ Detect behavioral anomalies

✅ Flag suspicious transactions automatically

---

## 🏗️ System Architecture

```text
┌────────────────────┐
│ Customer Profiles  │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Transaction Engine │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Kafka Producer     │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Kafka Topic        │
│  transactions      │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Kafka Consumer     │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ MongoDB Atlas      │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Change Streams     │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ OpenAI Embeddings  │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Atlas Vector Search│
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Fraud Engine       │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Fraud Alerts       │
└────────────────────┘
```

---

# 🛠️ Tech Stack

### Backend

* ☕ Java 21
* 🌱 Spring Boot
* 🌱 Spring Data MongoDB
* 🌱 Spring Kafka
* 🌱 Spring AI

### Messaging

* 📨 Apache Kafka

### Database

* 🍃 MongoDB Atlas
* 🔍 MongoDB Atlas Vector Search

### AI & Embeddings

* 🤖 OpenAI Embeddings
* 🤖 Spring AI

### Build & Development

* 🔨 Maven
* 💻 IntelliJ IDEA
* 🐙 Git
* 🌐 GitHub

---

# ⚡ Key Features

### 📊 Real-Time Transaction Processing

Continuously processes incoming financial transactions using Apache Kafka.

### 📨 Event-Driven Architecture

Transactions are published and consumed asynchronously through Kafka topics.

### 👤 Customer Behavior Profiling

Creates customer spending profiles including:

* Preferred merchants
* Spending categories
* Average spending habits
* Currency preferences

### 🤖 AI Embedding Generation

Converts transaction data into vector embeddings using OpenAI models.

Example:

```text
user_1 120 USD AMAZON TECH
```

↓

```text
[0.213, -0.842, 0.334, ...]
```

### 🔍 MongoDB Atlas Vector Search

Compares new transactions with historical customer transactions using vector similarity search.

### 🚨 Fraud Detection Engine

Flags suspicious transactions when:

* No similar historical transactions exist
* Similar transactions are already marked as fraud
* Spending behavior deviates significantly from normal patterns

---

# 🔄 End-to-End Workflow

### Step 1

Generate customer profiles.

### Step 2

Generate synthetic transactions.

### Step 3

Publish transactions to Kafka.

### Step 4

Consume Kafka events.

### Step 5

Store transactions in MongoDB Atlas.

### Step 6

Generate OpenAI embeddings.

### Step 7

Store embeddings in MongoDB.

### Step 8

Monitor new transactions using MongoDB Change Streams.

### Step 9

Perform Vector Search.

### Step 10

Evaluate fraud risk.

### Step 11

Flag suspicious transactions.

---

# 🗄️ MongoDB Atlas Setup

Create a database:

```text
fraud
```

Create collections:

```text
customers
transactions
```

---

# 🔍 Atlas Vector Search Index

Collection:

```text
transactions
```

Index Name:

```text
vector_index
```

Index Definition:

```json
{
  "fields": [
    {
      "type": "vector",
      "path": "embedding",
      "numDimensions": 1536,
      "similarity": "dotProduct"
    }
  ]
}
```

---

# 🤖 OpenAI Configuration

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.embedding.options.model=text-embedding-3-small
```

---

# 📨 Kafka Configuration

```properties
spring.kafka.bootstrap-servers=localhost:9092

spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer

spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

spring.kafka.consumer.group-id=fraud-group

spring.kafka.consumer.auto-offset-reset=earliest
```

---

# 🍃 MongoDB Configuration

```properties
spring.data.mongodb.uri=${MONGODB_URI}
spring.data.mongodb.database=fraud
```

---

# ⚙️ Running Kafka Locally

### Generate Cluster ID

```bash
bin/kafka-storage.sh random-uuid
```

### Format Storage

```bash
bin/kafka-storage.sh format --standalone -t <CLUSTER_ID> -c config/server.properties
```

### Start Kafka

```bash
bin/kafka-server-start.sh config/server.properties
```

### Create Topic

```bash
bin/kafka-topics.sh --create \
--topic transactions \
--bootstrap-server localhost:9092 \
--partitions 1 \
--replication-factor 1
```

### Verify Topic

```bash
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

---

# ▶️ Running the Application

### Clone Repository

```bash
git clone https://github.com/LavanyaSimham/vector-search-fraud-detection.git
```

### Navigate to Project

```bash
cd vector-search-fraud-detection
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

---

# 📁 Project Structure

```text
src
├── main
│   ├── java
│   │   ├── config
│   │   ├── enums
│   │   ├── model
│   │   ├── repository
│   │   ├── service
│   │   └── FraudApplication
│   │
│   └── resources
│       └── application.properties
│
└── test
```

---

# 🚨 Example Fraud Scenarios

### 💰 Unusual Spending

Normal:

```text
$50 - $100
```

Incoming:

```text
$2500
```

Result:

```text
Potential Fraud
```

---

### 🏪 Unknown Merchant

Normal:

```text
Amazon
Walmart
Costco
```

Incoming:

```text
Unknown Electronics Store
```

Result:

```text
Potential Fraud
```

---

### 🌍 Currency Change

Normal:

```text
USD
```

Incoming:

```text
EUR
```

Result:

```text
Potential Fraud
```

---

# 🚀 Future Enhancements

* Redis Caching
* Docker Containerization
* Kubernetes Deployment
* AWS Deployment
* Fraud Risk Scoring
* Real-Time Dashboard
* ML-Based Fraud Detection
* Multi-User Analytics

---

# 📚 Learning Outcomes

This project demonstrates practical experience with:

* Event-Driven Architecture
* Apache Kafka
* Spring Boot Microservices
* MongoDB Atlas
* Vector Databases
* AI Embeddings
* Spring AI
* Real-Time Fraud Detection
* Distributed Systems
* Financial Transaction Processing

---




