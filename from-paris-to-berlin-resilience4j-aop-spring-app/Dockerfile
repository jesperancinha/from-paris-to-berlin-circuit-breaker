FROM openjdk:17-slim-buster

WORKDIR /root

ENV LANG=C.UTF-8

COPY entrypoint.sh /root

COPY target/from-paris-to-berlin-resilience4j-aop-spring-app-*.jar /root/from-paris-to-berlin-resilience4j-aop-spring-app.jar

ENTRYPOINT ["/root/entrypoint.sh"]

EXPOSE 8085
