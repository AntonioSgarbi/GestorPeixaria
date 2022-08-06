FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE=target/GestorPeixaria-0.0.1-SNAPSHOT.jar

COPY $JAR_FILE application.jar

ENV MAIL_PROFILE_SENDER sendgrid

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

ENV SENDGRID_API_KEY api_key
ENV SENDGRID_FROM_NAME from
ENV SENDGRID_FROM_MAIL from_mail

ENV MAILGUN_API_USERNAME username
ENV MAILGUN_API_PASSWORD password
ENV MAILGUN_BASE_URL base_url
ENV MAILGUN_MESSAGES_URL messages_url

ENV UUID_JWT_SECRET secret
ENV JWT_EXPIRATION_TIME 3600
ENV JWT_REFRESH_TIME 3600
ENV GENERATE_KEY senha
ENV URL_CORS 'http://localhost:4200'

ENTRYPOINT ["java", "-jar", "application.jar",\
            "--server.port=${SERVER_PORT}",\
            "--spring.profiles.active=${MAIL_PROFILE_SENDER}",\
            "--spring.datasource.username=${DB_USER}",\
            "--spring.datasource.password=${DB_PASSWORD}",\
            "--spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}",\
            "--spring.mail.host=${MAIL_HOST}",\
            "--spring.mail.port=${MAIL_PORT}",\
            "--spring.mail.protocol=${MAIL_PROTOCOL}",\
            "--spring.mail.username=${MAIL_USERNAME}",\
            "--spring.mail.password=${MAIL_PASSWORD}",\
            "--spring.sendgrid.api-key=${SENDGRID_API_KEY}",\
            "--mailgun.api-key=${MAILGUN_API_KEY}",\
            "--mailgun.api.username=${MAILGUN_API_USERNAME}",\
            "--mailgun.api.password=${MAILGUN_API_PASSWORD}",\
            "--mailgun.base.url=${MAILGUN_BASE_URL}",\
            "--mailgun.messages.url=${MAILGUN_MESSAGES_URL}",\
            "--personal.mail.fromMail=${SENDGRID_FROM_MAIL}",\
            "--personal.mail.fromName=${SENDGRID_FROM_NAME}",\
            "--personal.security.jwtSecret=${UUID_JWT_SECRET}",\
            "--personal.security.jwtExpirationTime=${JWT_EXPIRATION_TIME}",\
            "--personal.security.jwtRefreshTime=${JWT_REFRESH_TIME}",\
            "--personal.security.passwordGenerate=${GENERATE_KEY}",\
            "--personal.security.urlCors=${URL_CORS}"]
