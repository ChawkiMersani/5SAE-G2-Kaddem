FROM openjdk:14
EXPOSE 8089
ADD target/kaddem-1.0.0.jar kaddem-1.0.0.jar

# Add a delay to wait for the database container to become available
CMD ["/bin/sh", "-c", "sleep 30 && java -jar kaddem-1.0.0.jar"]
