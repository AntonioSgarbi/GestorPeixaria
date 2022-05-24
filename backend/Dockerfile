FROM adoptopenjdk:17-jre-hotspot

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} application.jar

ENV PROFILE test
ENV SERVER_PORT 8080
ENV DB_USER postgres
ENV DB_PASS postgres

ENTRYPOINT ["java", "-jar", "application.jar",
            "--server.port=$SERVER_PORT",
            "--spring.active.profile=$PROFILE",
            "--spring.datasource.username=$DB_USER",
            "--spring.datasource.password=$DB_PASS"
            ]
