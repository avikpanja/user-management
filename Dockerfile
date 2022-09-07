# Build a JAR File
# For Java 11, try this
FROM maven:3.8.2-jdk-11-slim AS stage1
WORKDIR /home/app
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml dependency:go-offline
COPY src/ /home/app/src/
RUN mvn -f /home/app/pom.xml package

# Create an Image
FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8082
COPY --from=stage1 /home/app/target/UserManagement.jar UserManagement.jar
ENTRYPOINT ["sh", "-c", "java -jar /UserManagement.jar"]
