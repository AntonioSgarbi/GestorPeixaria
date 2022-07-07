FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE=target/GestorPeixaria-0.0.1-SNAPSHOT.jar

COPY $JAR_FILE application.jar

ENV SERVER_PORT 8080

ENV DB_HOST localhost
ENV DB_PORT 5432
ENV DB_NAME gestor_peixaria
ENV DB_USER postgres
ENV DB_PASSWORD postgres

ENV MAIL_HOST smtp-mail.outlook.com
ENV MAIL_PORT 587
ENV MAIL_PROTOCOL smtp
ENV MAIL_USERNAME username
ENV MAIL_PASSWORD password

ENV UUID_JWT_SECRET secret
ENV JWT_EXPIRATION_TIME 3600
ENV JWT_REFRESH_TIME 3600
ENV GENERATE_KEY senha
ENV URL_CORS 'http://localhost:4200'

ENTRYPOINT ["java", "-jar", "application.jar",\
            "--server.port=$SERVER_PORT",\
            "--spring.datasource.username=$DB_USER",\
            "--spring.datasource.password=$DB_PASSWORD",\
            "--spring.datasource.url=jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME",\
            "--spring.mail.host=$MAIL_HOST",\
            "--spring.mail.port=$MAIL_PORT",\
            "--spring.mail.protocol=$MAIL_PROTOCOL",\
            "--spring.mail.username=$MAIL_USERNAME",\
            "--spring.mail.password=$MAIL_PASSWORD",\
            "--personal.security.jwtSecret=$UUID_JWT_SECRET",\
            "--personal.security.jwtExpirationTime=$JWT_EXPIRATION_TIME",\
            "--personal.security.jwtRefreshTime=$JWT_REFRESH_TIME",\
            "--personal.security.nova-senha=$GENERATE_KEY",\
            "--personal.security.urlCors=$URL_CORS"]
