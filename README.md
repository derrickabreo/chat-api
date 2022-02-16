# Chat APIs

### This is a REST application used for user management and message services.

## Swagger 3
- Java 8
- Spring boot 2.4.2
- MongoDB 4.4.3
- Maven 3.6.3
- Swagger

## MongoDB
- You will need to update the default MongoDB URI spring.data.mongodb.uri in the application.properties file.
- 

## Commands
- Start an instance of mongoDB on you machine using the command `mongosh`
- Start the server in a console with `mvn spring-boot:run`.
- If you add some Unit Tests, you would start them with `mvn clean test`.
- Once application start up is complete, open swaggerUI (instructions below)

## Swagger 3
- Swagger 3 is already configured in this project in `SpringFoxConfig.java`.
- The Swagger UI can be seen at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## APIs included
#### Sample response and request can be found in SwaggerUI
- User Management
  - `/createUser` - `POST`, Creates a new user.
    - This API requires new three parameters as input `firstName`,`lastname` and `userName`. 
  - `/getAllUsers` - `GET`, Fetches all users from the Database
  - `/getUser/{id}` - `GET`, This API can be used to fetch individual user detail based on unique ID
- Messaging services
  - `/getMessages` - `POST`, Gets All the messages between two user using the unique IDs
    - This API requires a request body with `fromID` and `toID`
  - `/getConvoList/{userId}` - `GET` Gets a list of users that have sent or received message to/from a specified user.
    - Unique user ID needs to be passed as a query parameter
  - `/sendMessage` -`PUT` - This API is used send messages between two users
    - This API requires three parameters as input `fromId`,`message` and `toId`.
  