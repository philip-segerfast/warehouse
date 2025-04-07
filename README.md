# warehouse

## Features and technologies

Here's a list of features:
- Load data from JSON file
- Sell products
- See product stock

Here's a list of technologies and practices used:
- Kotlin
- Clean architecture
- H2 database with Exposed
  - Why Exposed: It's Kotlin's main ORM library and it's a popular choice in the Kotlin community.
  - Other options than Exposed: Spring JPA.
  - Why H2: It's a good database to get up to speed. It's simple and requires minimum setup.
  - Other options than H2: PostgreSQL for more production-ready database. Has more advanced and extensive feature-set.
- Ktor
  - Why: Since I'm using Kotlin Ktor is a good choice and this is project has a smaller scope. If it was a massive project then Spring Boot would be more relevant. 
  - Other options: Spring Boot. However, this is Java first and not always very Kotlin friendly.

## Building & Running

### Using gradle
To build or run the project, use one of the following tasks:

| Task                          | Description                                    |
|-------------------------------|------------------------------------------------|
| `./gradlew test`              | Run the tests                                  |
| `./gradlew build`             | Build everything                               |
| `run`                         | Run the server                                 |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

### Using Docker Compose (IaC)
In order to run this app using docker compose you can follow these steps:
1. Make sure docker compose is installed. If not you can [follow these instructions](https://docs.docker.com/compose/install/).
2. Navigate to the project directory using the terminal
3. Run the command `docker compose up -d`
4. Access the endpoints using postman (or whatever you like, but you can use postman to check body signatures)
5. To stop the containers you can run `docker compose down`

### Using Docker directly
To run this project using Docker you can follow these steps:
1. Make sure that Docker is installed on your computer.
2. Navigate to the project root using a Terminal
3. Run the command `docker build -t warehouse-app .` to build an image
4. Run the command `docker run -p 8080:8080 warehouse-app` to run the image in a container
5. Access the endpoints

## Endpoints
Server IP if running through Docker: `0.0.0.0:8080`  
Server IP if running directly through IDE: `127.0.0.1:8080`  
Note: Check the postman link below to view the body payloads for the requests.  
**Note: If running application directly without a docker container you need to change the Postman URL to match.**

| Endpoint              | Type | Description                                                    |
|-----------------------|------|----------------------------------------------------------------|
| `/products/inventory` | GET  | List all products and show how many are in stock               |
| `/products/sell`      | GET  | Sell the specified product. See Postman link for body.         |
| `/admin/restore`      | POST | Restore all data. Will load data from json file into database. |

**[>> Postman link here <<](https://www.postman.com/flight-astronomer-26395313/public/collection/b3kic7w/warehouse?action=share&creator=14762181)**

## Core app components

App entry point: `framework/ktor/Application.kt`

## Things I haven't implemented/things to improve
- Should create more tests and use mocks to test interactions
- Could make some endpoint tests
- Create more endpoints
- Add logging
