FROM openjdk:17-slim-buster

WORKDIR /root

ENV LANG=C.UTF-8

COPY entrypoint.sh /root

COPY target/from-paris-to-berlin-ws-service.jar /root/from-paris-to-berlin-ws-service.jar

ENTRYPOINT ["/root/entrypoint.sh"]

EXPOSE 8081
