FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests package (windows: mvnw)
 
FROM eclipse-temurin:21-jdk
WORDIR /app
COPY --from=builder /app/target/*.jar app.jar
 
EXPOSE 8080
 
ENTRYPOINT ["java", "-jar", "app.jar"]
