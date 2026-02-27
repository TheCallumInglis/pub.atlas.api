# Pub Atlas API

Backend API for tracking visited pubs (And maybe other stuff in the future?).

A sandbox project to learn Quarkus and have fun with pub data.

## Tech Stack

- Quarkus 3
- PostgreSQL
- Hibernate ORM (Panache)
- Flyway
- Docker (Dev Services)

## Run Locally

```bash
./mvnw quarkus:dev
```

> [!IMPORTANT]  
> Note: Dev Services will automatically start a PostgreSQL instance in a Docker container for you. Make sure you have Docker installed and running.

## API Collection

Bruno collection available [here](./bruno/pub.atlas.api/)

Get Bruno: [https://www.getbruno.com/](https://www.getbruno.com/)