# Partner Service

This is a simple RESTFUL service for managing partners.

## Running the Service Locally

To run the service locally, you'll need to install the following dependencies:

- Java 8 or higher
- Maven 3.6.0 or higher
- Docker 20.10.7 or higher

Once you have the dependencies installed, follow these steps:

1. Clone the repository: `git clone https://github.com/Parmidajabbari/partner.git`
2. Navigate to the root directory of the project: `cd partner`
3. Build the project: `mvn clean package`
4. Start the MongoDB container: `docker-compose up -d`
5. Start the service: `java -jar target/partner-0.0.1-SNAPSHOT.jar`

The service should now be running on [http://localhost:8080/partners. ↗](http://localhost:8080/partners.)

## Deploying the Service

To deploy the service, you'll need to have a server with Java and Docker installed. Follow these steps:

1. Clone the repository: `git clone https://github.com/Parmidajabbari/partner.git`
2. Navigate to the root directory of the project: `cd partner`
3. Build the Docker image: `docker build -t partner .`
4. Start the MongoDB container: `docker-compose up -d`
5. Start the service container: `docker run -d -p 8080:8080 --name partner-service partner`

The service should now be running on http://<server-ip>:8080/partners.

## Running the Tests

To run the tests, follow these steps:

1. Navigate to the root directory of the project: `cd partner`
2. Run the tests: `mvn test`

The tests should run and show the results in the console.

## API Documentation

The service provides the following endpoints:

- `GET /partners/search`: Return the partner that is nearest with the given lat and lng.
- `GET /partners/{id}`: Returns the partner with the specified ID.
- `POST /partners`: Creates a new partner.

The request and response formats for each endpoint are documented in the Swagger UI. To access the Swagger UI, go to [http://localhost:8080/swagger-ui.html ↗](http://localhost:8080/swagger-ui.html) when running the service locally, or http://<server-ip>:8080/swagger-ui.html when running the deployed service.
