FROM openjdk:11
EXPOSE 8085
ADD target/blogadmin.jar blogadmin.jar
ENTRYPOINT ["java", "-jar", "/blogadmin.jar"]