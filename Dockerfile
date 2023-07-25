FROM openjdk:8-jre
MAINTAINER lw
WORKDIR /
ADD target/springboot-demo-tools.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]


