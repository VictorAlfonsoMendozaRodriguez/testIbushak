### Requirements
* [Java](https://www.java.com/download/)
* [Maven](https://maven.apache.org/)

### Setup

# Server Setup
#### windows check if is mvn or mvnw
* Run `mvn install` or `mvnv install`  install dependencies & build source
* Run `mvn spring-boot:run` or `mvnw spring-boot:run` to start server

# Server Setup Docker
* Run `./mvnw install`  install dependencies & build source
* Run `./mvnw package -Dmaven.test.skip` create jar in target
* Run `docker compose up -d` execute docker-compose.yml

#test swagger
http://localhost:8080/swagger-ui/index.html

