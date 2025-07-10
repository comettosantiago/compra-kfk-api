Example of Event Driven Architecture Java API that manages purchases, shipping and billing using Kafka for async communitacion between services. 

This simple API receives a cart purchase via HTTP request and persists it in a relational database using Hibernate JPA.
After saving it, the persisted entity is used to produce an event to a Kafka topic. This event is consumed by `envio-kfk-api` and `factura-kfk-api` microservices to create their respective entities and produce new events.
Then, these events are consumed back by this main API to update the purchase entity.

This basic EDA API is used as an example of a scalable and modular architecture.


## Running the project

There's a `kafka` folder in the root that contains the `docker-compose.yml` and `init.sql` used to execute the needed services.
### Services runned by the Docker-Compose
- PostgreSQL (db service with .sql script to create the needed tables)
- Kafka (broker service)
- Zookeeper (kafka dependency)

Run the following command inside the `kafka` folder to run the docker compose:
```
docker compose up -d
```

### Needed APIs
- `envio-kfk-api`
- `factura-kfk-api`

**Note:** These APIs should be manually executed from your IDE or using Maven.
