FROM openjdk:11-jdk-oracle
ADD /target/order-0.0.1-SNAPSHOT.jar order.jar
ENTRYPOINT ["java", "-jar", "order.jar"]
EXPOSE 8081
