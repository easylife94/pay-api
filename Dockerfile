FROM hub.c.163.com/library/java:8-alpine
ENV LANG C.UTF-8
ADD pay-api-web/target/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","app.jar"]