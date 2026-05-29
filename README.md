# iGovern Research Management System

## Overview

iGovern Research Management System is a full-stack research program management platform built to manage research programs, participant enrollment, consent file handling, and audit tracking.

The application supports:

* Research Program Management
* Participant Enrollment
* Consent File Upload & Download
* Audit Logging using ActiveMQ
* Dockerized Deployment using Docker Compose

---

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Security
* PostgreSQL
* ActiveMQ
* Maven

### Frontend

* Angular 20
* TypeScript
* HTML / CSS

### DevOps / Deployment

* Docker
* Docker Compose
* Nginx

---

## Features

### Research Program Management

* Create Research Programs
* View Research Programs
* Update Programs
* Delete Programs

Program Details:

* Program Code
* Title
* Description
* Budget
* Start Date
* End Date
* Status

---

### Participant Management

* Add Participants to Programs
* View Participants by Program
* Update Participant Details
* Delete Participants

Participant Details:

* Name
* Email
* Phone Number
* Age
* Gender
* Enrollment Date
* Medical Condition

---

### Consent File Management

Participants can upload consent documents.

Supported file formats:

* PDF
* PNG
* JPG / JPEG

Features:

* Upload Consent File
* Download Consent File
* File Type Validation
* Disabled Download Button if no file exists

---

### Audit Logging

The system tracks participant deletion events using ActiveMQ.

Audit information includes:

* Event Type
* Entity
* Entity ID
* Deleted By
* Deleted Timestamp

Example Event:

* PARTICIPANT_DELETED

---

## Project Structure

```text
igovern-assessment/
│
├── audit-service/           # Audit logging microservice
├── program-service/         # Program & participant service
├── igovern-ui/              # Angular frontend
├── docker-compose.yml
├── init.sql
└── README.md
```

---

## Prerequisites

Install the following:

* Docker Desktop
* Git

Verify Docker installation:

```bash
docker --version
docker compose version
```

---

## Run Application using Docker

### Step 1: Clone Repository

```bash
git clone <repository-url>
```

Navigate to project:

```bash
cd igovern-assessment
```

---

### Step 2: Run Application

Run:

```bash
docker compose up --build
```

This command will:

* Start PostgreSQL
* Start ActiveMQ
* Start Program Service
* Start Audit Service
* Build and Start Angular UI

---

## Application URLs

### Frontend UI

```text
http://localhost:4200
```

### Program Service Swagger

```text
http://localhost:8081/swagger-ui/index.html
```

### Audit Service Swagger

```text
http://localhost:8082/swagger-ui/index.html
```

### ActiveMQ Console

```text
http://localhost:8161/admin
```

Credentials:

Username:

```text
admin
```

Password:

```text
admin
```

---

## Database

PostgreSQL Database:

```text
Database Name: igovern_db
```

Schemas:

```text
program_schema
audit_schema
```

---

## API Highlights

### Program APIs

* Create Program
* Get Program By ID
* Get All Programs
* Update Program
* Delete Program

### Participant APIs

* Add Participant
* Get Participant By ID
* Get Participants By Program
* Update Participant
* Delete Participant
* Upload Consent File
* Download Consent File

### Audit APIs

* Get Audit Logs

---

## Validations Implemented

### Program Validations

* Required Fields
* End Date Mandatory
* Budget Validation

### Participant Validations

* Email Validation
* Duplicate Participant Email Validation within Program
* Mandatory Fields Validation

### File Upload Validation

Allowed file types only:

```text
.pdf
.png
.jpg
.jpeg
```

---

## Docker Services

The application runs with the following containers:

* PostgreSQL
* ActiveMQ
* Program Service
* Audit Service
* Angular UI (Nginx)

Check running containers:

```bash
docker ps
```

Stop application:

```bash
docker compose down
```

---

## Future Enhancements

* Authentication & Authorization
* Dashboard Analytics
* Pagination
* Search & Filtering
* Cloud Deployment
* CI/CD Pipeline Integration

---

## Author

Developed as part of an assessment project for research program management.
