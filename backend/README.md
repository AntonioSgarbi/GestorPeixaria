# [<img src="https://img.shields.io/badge/dockerhub-image-success.svg?logo=docker">](<LINK>)
### run this project:
#### docker:
``` 
docker run --net=host -p 8080:8080 -e MAIL_USERNAME={your_email@email.com} -e MAIL_PASSWORD={password} -e MAIL_HOST={smtp-mail.outlook.com} -e GENERATE_KEY={encripted0nBoot} antoniosk/gestor_peixaria:latest
```
#### docker-compose:
```
docker-compose up -d
```
