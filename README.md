# Application to manage user data and track user events.

## Prerequisites
1. Maven
2. Docker

## Technologies & Packages
1. Spring Boot 3
2. Spring Data JPA
3. PostgreSQL
4. Kafka
5. Redis
6. GSON
7. docker-compose.yaml

N-layer architecture is used to build this application. Followed clean code principles.

## Setup Steps:
1. **Clone the application**

   ```bash
    git clone https://github.com/sakib-personal/user-management-bs23.git
   ```

2. **Move to the root directory the project**
   ```bash
    cd user-management-bs23
   ```

3. **Build the application using maven**

   ```bash
    mvn clean install -DskipTests
   ```

4. **Build docker image**

   ```bash
    docker-compose build
   ```

5. **Create & up docker container**

   ```bash
    docker-compose up
   ```

7. **API Setup with data in Postman**
    + Add new user using POST api.
       ```
        URL: http://localhost:8080/users
       ```
       ```
       JSON: {"firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "dateOfBirth": "1990-01-01"}
       ```
    + Get existing user by id using GET api.
       ```
        URL: http://localhost:8080/users/{userId}
       ```
   + Edit existing user by id using PUT api.
      ```
       URL: http://localhost:8080/users/{userId}
      ```
      ```
      JSON: {"firstName": "Jhong", "lastName": "Doe", "email": "john.doe@example.com", "dateOfBirth": "1990-01-01"}
      ```
   + Delete existing user by id using DELETE api.
      ```
       URL: http://localhost:8080/users/{userId}
      ```

## Remaining Tasks (Improvements)
1. Redis added in docker-compose.yaml except code implementation.
2. A frontend application needs to be created to visualize this APIs data. React JS can be most suitable for this as it is very light weight.
3. Most importantly unit & integration test cases needs to be added which can increased the confidence level of the current codebase.