# Use an openjdk image as the base image
FROM openjdk:17-oracle

# Set the working directory
WORKDIR /app

# Copy the jar file to the container
COPY target/*.spring-boot-jpa-crud-example-0.0.1-SNAPSHOT.jar  /app/spring-boot-jpa-crud-example-0.0.1-SNAPSHOT.jar

# Expose the default port for a Spring Boot application
EXPOSE 8080

# Set the command to run the jar file
CMD ["java", "-jar", "spring-boot-jpa-crud-example-0.0.1-SNAPSHOT.jar"]


#This Dockerfile starts from an openjdk base image, sets the working 
#directory to /app, and then copies the jar file for your Spring Boot 
#application to the container. It then exposes port 8080, which is the 
#default port for a Spring Boot application. Finally, it sets the command 
#to run the jar file using the java command.

#You can build the Docker image using the following command:

#		docker build -t spring-boot-jpa-crud-example

#And run the Docker container using the following command:

#	docker run -p 8080:8080 spring-boot-jpa-crud-example

#This will map port 8080 on the host to port 8080 in the Docker container, 
#allowing you to access the Spring Boot application from the host.
